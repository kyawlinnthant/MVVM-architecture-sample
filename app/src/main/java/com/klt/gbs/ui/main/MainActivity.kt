package com.klt.gbs.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.klt.gbs.R
import com.klt.gbs.base.BaseActivity
import com.klt.gbs.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MainIndicator {

    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    override fun initUi() {
        viewModel.getListByType("popular")
    }

    override fun observe() {
        viewModel.movieList.observe(this){
            Timber.tag(this::class.java.simpleName).d(it.toString())
        }
    }

    override fun setupNavigation() {
        //get host and controller
        val navController = findNavController(R.id.container)
        //set controller with view
        binding.bottomNavigationView.setupWithNavController(navController)
        //set top-lvl navigationController with appBar
        setupActionBarWithNavController(
            navController, AppBarConfiguration(
                topLevelDestinationIds = setOf(
                    R.id.popular,
                    R.id.upcoming
                )
            )
        )
    }

}