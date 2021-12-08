package com.example.cp3_online_doctor_consultation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.nav_header.*

class DashboardActivity : AppCompatActivity(){

    lateinit var toggle : ActionBarDrawerToggle
//    lateinit var btnToProfilePage : Button

    lateinit var demoFragment: DemoFragment
//    lateinit var consultFragment: ConsultFragment
    lateinit var diseaseFragment: DiseaseFragment
    lateinit var dashboardFragment: DashboardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        demoFragment = DemoFragment()
        diseaseFragment = DiseaseFragment()
        dashboardFragment = DashboardFragment()
//        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrame, diseaseFragment, "diseaseFrag")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .setReorderingAllowed(true)
            .commit()

        nav_View.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.itm_goProfilePage -> {
                    Log.d(TAG, "Correct")
                    Intent(this, ProfileActivity::class.java).also {
                        startActivity(it)
                        Log.d(TAG, "Incorrect")
                    }
                }
//                R.id.itm_YourWallet -> Toast.makeText(
//                        this@DashboardActivity, "Cliked on Your Wallet",
//                        Toast.LENGTH_SHORT,
//                ).show()
//
//                R.id.itm_YourOrders -> Toast.makeText(
//                        this@DashboardActivity, "Cliked on Your Orders",
//                        Toast.LENGTH_SHORT).show()

                R.id.itm_MedicalRecords -> Toast.makeText(
                        this@DashboardActivity, "Cliked on Your Medical Records",
                        Toast.LENGTH_SHORT).show()

                R.id.itm_ReadHealth -> Toast.makeText(
                        this@DashboardActivity, "Cliked on Read Health related News",
                        Toast.LENGTH_SHORT).show()

                R.id.itm_HelpCenter -> Toast.makeText(
                        this@DashboardActivity, "Cliked on Help Center",
                        Toast.LENGTH_SHORT).show()

                R.id.itm_RateUs -> Toast.makeText(
                        this@DashboardActivity, "Clicked on Rate Us",
                        Toast.LENGTH_SHORT).show()

                R.id.itm_CheckEvents -> Toast.makeText(
                        this@DashboardActivity, "Cliked on Check for any Events",
                        Toast.LENGTH_SHORT).show()

                R.id.itm_Logout -> {
                    signOut()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.layout_header_menu, menu);
//        return true;
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        Intent(this, LoginActivity::class.java).also{
            startActivity(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        val TAG = "DashboardActivity"
    }
}
