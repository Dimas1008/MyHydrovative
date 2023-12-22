package com.example.myhydrovative.ui.fragment.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.FragmentCameraBinding
import com.example.myhydrovative.ml.ModelJenisTanaman2
import com.example.myhydrovative.ui.createCustomTempFile
import com.example.myhydrovative.ui.rotateBitmap
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder

class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private var currentImageUri: Uri? = null
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.imageBtnCamera.setOnClickListener { takePicture() }
        binding.imageBtnImage.setOnClickListener { startGallery() }
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)

        createCustomTempFile(requireActivity().application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.example.myhydrovative",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = rotateBitmap(
                BitmapFactory.decodeFile(getFile?.path),
                true
            )
            binding.ivDeteksiTanaman.setImageBitmap(result)

            // Klasifikasi gambar setelah gambar ditampilkan
            val prediction = classifyImage(result)
            binding.textViewResult.text = "Prediction: $prediction"
            // Toast.makeText(requireContext(), "Predicted: $prediction", Toast.LENGTH_LONG).show()
        }
    }

    private fun startGallery() {
        if (allPermissionsGranted()) {
            launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivDeteksiTanaman.setImageURI(it)

            // Klasifikasi gambar setelah gambar dari galeri ditampilkan
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, it)
            val resizedBitmap = resizeBitmap(bitmap, 150, 150) // Sesuaikan ukuran sesuai kebutuhan model Anda
            val prediction = classifyImage(resizedBitmap)
            binding.textViewResult.text = "Prediction: $prediction"
            // Toast.makeText(requireContext(), "Predicted: $prediction", Toast.LENGTH_LONG).show()
        }
    }

    private fun classifyImage(bitmap: Bitmap): String {
        val model = context?.let { ModelJenisTanaman2.newInstance(it) }

        if (model == null) {
            return "Error: Model could not be loaded."
        }

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
        val inputData = ByteBuffer.allocateDirect(150 * 150 * 3 * 4)
        inputData.order(ByteOrder.nativeOrder())
        val floatBuffer = inputData.asFloatBuffer()

        val pixels = IntArray(150 * 150)

        // Memastikan bitmap memiliki ukuran yang cukup
        if(bitmap.width < 150 || bitmap.height < 150) {
            return "Error: Bitmap size is too small"
        }

        bitmap.getPixels(pixels, 0, 150, 0, 0, 150, 150)
        for (pixelValue in pixels) {
            floatBuffer.put(((pixelValue shr 16 and 0xFF) - 127.5f) / 127.5f)
            floatBuffer.put(((pixelValue shr 8 and 0xFF) - 127.5f) / 127.5f)
            floatBuffer.put(((pixelValue and 0xFF) - 127.5f) / 127.5f)
        }
        inputFeature0.loadBuffer(inputData)

        val outputs = model.process(inputFeature0)
        val probabilities = outputs?.outputFeature0AsTensorBuffer?.floatArray

        var maxProb = -1.0f
        var predictedClass = -1
        probabilities?.forEachIndexed { index, prob ->
            if (prob > maxProb) {
                maxProb = prob
                predictedClass = index
            }
        }

        model.close()

        return "Class Name for ID $predictedClass"
    }

    private fun resizeBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}