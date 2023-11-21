package com.example.myhydrovative.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.commit
import com.example.myhydrovative.R
import com.example.myhydrovative.ui.activity.main.MainActivity

class ProfileFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Implement fragment logic here
        val btnDetailCategory:CardView = view.findViewById(R.id.cardViewLogout)
        btnDetailCategory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.cardViewLogout) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)

            // Akhiri activity saat ini (ProfileFragment)
            requireActivity().finish()
        }
    }
}