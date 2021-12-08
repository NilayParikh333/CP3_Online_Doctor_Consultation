package com.example.cp3_online_doctor_consultation.AdapterClass

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cp3_online_doctor_consultation.LifestyleProfileFragment
import com.example.cp3_online_doctor_consultation.MedicalProfileFragment
import com.example.cp3_online_doctor_consultation.PersonalProfileFragment

class ViewPagerAdapter(private val myContext: Context, fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return PersonalProfileFragment()
            }
            1 -> {
                return MedicalProfileFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return LifestyleProfileFragment()
            }
            else -> return PersonalProfileFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }

}