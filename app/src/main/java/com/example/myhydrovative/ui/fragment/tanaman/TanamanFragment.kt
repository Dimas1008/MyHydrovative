package com.example.myhydrovative.ui.fragment.tanaman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.myhydrovative.R
import com.example.myhydrovative.ui.fragment.tablayout.PanenFragment
import com.example.myhydrovative.ui.fragment.tablayout.TanamFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TanamanFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tanaman, container, false)

        // Inisialisasi TabLayout dan ViewPager
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager2 = view.findViewById(R.id.view_pager_2)


        // Panggil metode untuk menyiapkan tabs
        setupViewPager2()

        return view
    }

    private fun setupViewPager2() {
        // Buat objek adapter untuk mengelola fragments
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        // Tambahkan fragments ke adapter
        adapter.addFragment(TanamFragment(), "Ditanam")
        adapter.addFragment(PanenFragment(), "Sudah Panen")

        // Atur adapter untuk ViewPager2
        viewPager2.adapter = adapter

        // Hubungkan ViewPager2 dengan TabLayout
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }

}