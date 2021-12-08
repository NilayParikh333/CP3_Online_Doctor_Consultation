package com.example.cp3_online_doctor_consultation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.cp3_online_doctor_consultation.AdapterClass.ViewPagerAdapter
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

//    lateinit var mainTabLayout: TabLayout
//    lateinit var personalTab: TabItem
//    lateinit var medicalTab: TabItem
//    lateinit var lifestyleTab: TabItem
//    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title ="Profile"

        tabLayout = findViewById(R.id.mainTabLayout)
//        tabLayout = LayoutInflater.from(parent.applicationContext).inflate(R.id.mainTabLayout,parent,false)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Personal"))
        tabLayout.addTab(tabLayout.newTab().setText("Medical"))
        tabLayout.addTab(tabLayout.newTab().setText("Lifestyle"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPagerAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                Log.d("Tab Position", tab.position.toString())
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


//        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

//        val viewPagerAdapter = null
//        ViewPagerAdapter.addFragment(PersonalProfileFragment(), "Personal")
//        viewPagerAdapter.addFragment(MedicalProfileFragment(), "Medical")
//        viewPagerAdapter.addFragment(LifestyleProfileFragment(), "Lifestyle")
//
//        viewPager.adapter = viewPagerAdapter
//        mainTabLayout.setupWithViewPager(viewPager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
