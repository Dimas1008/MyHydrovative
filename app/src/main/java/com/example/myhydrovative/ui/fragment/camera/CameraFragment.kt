package com.example.myhydrovative.ui.fragment.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
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
import com.example.myhydrovative.ui.createCustomTempFile
import com.example.myhydrovative.ui.rotateBitmap
import java.io.File

class CameraFragment : Fragment() {

    // Binding untuk mengakses elemen UI dalam layout Fragment
    private lateinit var binding: FragmentCameraBinding

    // URI untuk menyimpan alamat gambar yang dipilih dari galeri
    private var currentImageUri: Uri? = null

    private lateinit var currentPhotoPath: String

    private var getFile: File? = null

    // Launcher untuk meminta izin kamera
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Permission request denied", Toast.LENGTH_LONG)
                    .show()
            }
        }

    // Fungsi untuk memeriksa apakah semua izin yang diperlukan telah diberikan
    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    // Fungsi utama untuk membuat tampilan Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment menggunakan view binding
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Fungsi yang dipanggil setelah tampilan telah dibuat
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        // Mengatur OnClickListener untuk tombol camera
        binding.imageBtnCamera.setOnClickListener { takePicture() }

        // Mengatur OnClickListener untuk tombol gambar
        binding.imageBtnImage.setOnClickListener { startGallery() }
    }

    // Fungsi untuk memulai camera ketika tombol camera diklik
    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager) // Menggunakan requireActivity() untuk mengakses packageManager dari aktivitas yang terkait dengan fragment

        createCustomTempFile(requireActivity().application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),  // Menggunakan requireActivity() untuk mengakses context dari aktivitas yang terkait dengan fragment
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
        }
    }

    // Fungsi untuk memulai galeri ketika tombol gambar diklik
    private fun startGallery() {
        if (allPermissionsGranted()) {
            // Jika izin kamera telah diberikan, maka buka galeri
            launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            // Jika izin belum diberikan, minta izin kamera
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
    }

    // Launcher untuk memilih gambar dari galeri
    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            // Jika gambar dipilih, simpan URI dan tampilkan gambar
            currentImageUri = uri
            showImage()
        } else {
            // Jika tidak ada gambar yang dipilih, log pesan
            Log.d("Photo Picker", "No media selected")
        }
    }

    // Fungsi untuk menampilkan gambar yang dipilih ke ImageView
    private fun showImage() {
        currentImageUri?.let {
            // Log URI gambar yang ditampilkan
            Log.d("Image URI", "showImage: $it")
            // Set URI gambar ke ImageView menggunakan view binding
            binding.ivDeteksiTanaman.setImageURI(it)
        }
    }

    // Objek companion untuk menyimpan konstanta REQUIRED_PERMISSION
    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}