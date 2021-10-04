package com.klt.gbs.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.klt.gbs.util.InflateActivity
import com.klt.gbs.util.InflateFragment

abstract class BaseActivity<VB : ViewBinding>(private val inflate: InflateActivity<VB> ) : AppCompatActivity() {

    private var _binding : VB? = null
    val binding get() = _binding!!

    abstract fun initUi()
    abstract fun observe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)
        initUi()
        observe()

    }
}