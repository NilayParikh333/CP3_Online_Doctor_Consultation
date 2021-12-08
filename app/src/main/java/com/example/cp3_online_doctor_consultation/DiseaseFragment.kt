package com.example.cp3_online_doctor_consultation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_disease.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiseaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiseaseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disease, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DiseaseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DiseaseFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cIVCorona.setOnClickListener{
//            tvConsultTypeOfDoctor.text = tvCorona.text.toString()
//            val message=ed1.text.toString()
//            intent.putExtra("Key1",message)
            changeFragment("corona")
        }

        cIVGynaecology.setOnClickListener{
            changeFragment("gynaecology")
        }

        cIVAcne.setOnClickListener{
            changeFragment("acne")
        }

        cIVAcneScars.setOnClickListener{
            changeFragment("acnescars")
        }

        cIVBackPain.setOnClickListener{
            changeFragment("backpain")
        }

        cIVCarpalTunnelSyndrome.setOnClickListener{
            changeFragment("carpal tunnel syndrome")
        }

        cIVCoughing.setOnClickListener{
            changeFragment("coughing")
        }

        cIVDandruff.setOnClickListener{
            changeFragment("dandruff")
        }

        cIVDermatology.setOnClickListener {
            changeFragment("dermatology")
        }

        cIVDizziness.setOnClickListener {
            changeFragment("dizziness")
        }

        cIVEarNoseThroat.setOnClickListener {
            changeFragment("ear nose throat")
        }

        cIVEczema.setOnClickListener {
            changeFragment("eczema")
        }

        cIVFever.setOnClickListener {
            changeFragment("fever")
        }

        cIVFungalInfection.setOnClickListener {
            changeFragment("fungal infection")
        }

        cIVGeneralPhysician.setOnClickListener {
            changeFragment("general physician")
        }

        cIVHairLoss.setOnClickListener {
            changeFragment("hairloss")
        }

        cIVHeadaches.setOnClickListener {
            changeFragment("headaches")
        }

        cIVHighBP.setOnClickListener {
            changeFragment("highbp")
        }

        cIVIrregularPeriods.setOnClickListener {
            changeFragment("irregular periods")
        }

        cIVKneePain.setOnClickListener {
            changeFragment("knee pain")
        }

        cIVLegPain.setOnClickListener {
            changeFragment("leg pain")
        }

        cIVMenopause.setOnClickListener {
            changeFragment("menopause")
        }

        cIVMouthSores.setOnClickListener {
            changeFragment("mouthsores")
        }

        cIVOrthopedic.setOnClickListener {
            changeFragment("orthopedic")
        }

        cIVOvarianCysts.setOnClickListener {
            changeFragment("ovariancysts")
        }
        
        cIVVitiligo.setOnClickListener {
            changeFragment("vitiligo")
        }
        
        cIVPCOS.setOnClickListener {
            changeFragment("pcos")
        }
        
        cIVPneumonia.setOnClickListener {
            changeFragment("pneumonia")
        }

        cIVPediatrics.setOnClickListener {
            changeFragment("pediatrics")
        }

        cIVStomachAndDigestion.setOnClickListener {
            changeFragment("stomach and digestion")
        }

        cIVPsychiatry.setOnClickListener {
            changeFragment("psychiatry")
        }

        cIVSnoring.setOnClickListener {
            changeFragment("snoring")
        }

        cIVSoreThroat.setOnClickListener {
            changeFragment("sorethroat")
        }

        cIVVertigo.setOnClickListener {
            changeFragment("vertigo")
        }

        cIVStomachPain.setOnClickListener {
            changeFragment("stomach pain")
        }
    }

    private fun changeFragment(tag: String){
        val consultFragment = ConsultFragment()
        val doctorProfileActivity = doctorProfileActivity()

        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerFrame, consultFragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setReorderingAllowed(true)
                .addToBackStack("disease")
                .commit()
    }

}