package com.klt.gbs.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.klt.gbs.R
import com.klt.gbs.base.BaseActivity
import com.klt.gbs.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() , MainIndicator{

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun initUi() {
        setupNavigation()
    }

    override fun observe() {
        TODO("Not yet implemented")
    }

    override fun setupNavigation() {
        //get host and controller
        val navController = this.findNavController(R.id.container)
        //set graph
//        appBarConfiguration = AppBarConfiguration(R.menu.btn_nav_menu,navController.graph,null, false)
        //set appbar
//        appBarConfiguration = AppBarConfiguration(setOf(R.id.popular, R.id.upcoming))
        //set appbar
        setupActionBarWithNavController(navController, appBarConfiguration)
        //set bottom nav
        binding.bottomNavigationView.setupWithNavController(navController)
    }


}