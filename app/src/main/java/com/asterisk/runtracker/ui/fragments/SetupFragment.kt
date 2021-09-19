package com.asterisk.runtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.asterisk.runtracker.R
import com.asterisk.runtracker.databinding.FragmentSetupBinding
import com.asterisk.runtracker.databinding.FragmentTrackingBinding
import com.asterisk.runtracker.utils.Constants.KEY_FIRST_TIME_TOGGLE
import com.asterisk.runtracker.utils.Constants.KEY_NAME
import com.asterisk.runtracker.utils.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment: Fragment(R.layout.fragment_setup) {

    private var _binding: FragmentSetupBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @set:Inject
    var isFirstToOpenApp = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isFirstToOpenApp) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }

        binding.apply {

            tvContinue.setOnClickListener {
                val success = writePersonalDataToSharedPref()
                if (success) {
                    val action = SetupFragmentDirections.actionSetupFragmentToRunFragment()
                    findNavController().navigate(action)
                } else {
                    Snackbar.make(requireView(), "Please enter all fields", Snackbar.LENGTH_SHORT).show()
                }

            }
        }

    }


    private fun writePersonalDataToSharedPref(): Boolean {
        val name = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString()

        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }

        sharedPreferences.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()

        val toolbarText = "Let's go $name!"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}