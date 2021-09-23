package com.klt.gbs.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klt.gbs.base.BaseFragment
import com.klt.gbs.databinding.FragmentPopularBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : BaseFragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
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