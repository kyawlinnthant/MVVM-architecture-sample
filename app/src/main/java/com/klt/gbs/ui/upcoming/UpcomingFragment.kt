package com.klt.gbs.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klt.gbs.base.BaseFragment
import com.klt.gbs.databinding.FragmentUpcomingBinding

class UpcomingFragment : BaseFragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private var binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun observe() {
        TODO("Not yet implemented")
    }

    override fun initUi() {
        TODO("Not yet implemented")
    }
}