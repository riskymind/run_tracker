package com.asterisk.runtracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.asterisk.runtracker.R
import com.asterisk.runtracker.ui.viewmodels.MainViewModel
import com.asterisk.runtracker.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment: Fragment(R.layout.fragment_statistics) {

    private val viewModel by viewModels<StatisticsViewModel>()

}