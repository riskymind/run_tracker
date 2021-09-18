package com.asterisk.runtracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asterisk.runtracker.R
import com.asterisk.runtracker.databinding.FragmentSetupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupFragment: Fragment(R.layout.fragment_setup) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSetupBinding.bind(view)

        binding.apply {

            tvContinue.setOnClickListener {
                val action = SetupFragmentDirections.actionSetupFragmentToRunFragment()
                findNavController().navigate(action)
            }
        }

    }
}