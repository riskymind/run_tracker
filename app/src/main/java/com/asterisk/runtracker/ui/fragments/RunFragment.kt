package com.asterisk.runtracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asterisk.runtracker.R
import com.asterisk.runtracker.databinding.FragmentRunBinding
import com.asterisk.runtracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunFragment: Fragment(R.layout.fragment_run) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRunBinding.bind(view)

        binding.apply {

            fab.setOnClickListener {
                val action = RunFragmentDirections.actionRunFragmentToTrackingFragment()
                findNavController().navigate(action)
            }
        }
    }
}