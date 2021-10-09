package com.klt.gbs.ui.detail

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.klt.gbs.base.BaseActivity
import com.klt.gbs.databinding.ActivityDetailBinding
import com.klt.gbs.model.Movie
import com.klt.gbs.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    private val _id by lazy { intent?.getIntExtra(EXTRA_ID, 0) }

    companion object {
        private const val EXTRA_ID = "extra.movie.id"
        fun newIntent(context: Context?, id: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }

    override fun initUi() {
        viewModel.fetchData(_id!!)
        setupActionBar()
    }

    override fun observe() {
        viewModel.movieData.observe(this) {
            checkState(it)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.let {
            it.title = this.javaClass.simpleName
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupView(movie: Movie) {
        binding.title.text = movie.original_title
        binding.date.text = movie.release_date
        binding.vote.text = movie.vote_average.toString()
        binding.overview.text = movie.overview
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/" + movie.backdrop_path)
            .into(binding.img)
    }

    private fun checkState(movie: Resource<Movie>) {

        when (movie.status) {
            Resource.Status.LOADING -> {
                binding.viewLayout.visibility = View.GONE
                binding.viewLoadingState.loadingLayout.visibility = View.VISIBLE
            }
            Resource.Status.SUCCESS -> {
                binding.viewLayout.visibility = View.VISIBLE
                binding.viewLoadingState.loadingLayout.visibility = View.GONE
                binding.viewLoadingState.progress.visibility = View.GONE
                setupView(movie.data!!)
            }
            Resource.Status.ERROR -> {
                binding.viewLayout.visibility = View.GONE
                binding.viewLoadingState.loadingLayout.visibility = View.VISIBLE
                binding.viewLoadingState.errorText.visibility = View.VISIBLE
                binding.viewLoadingState.errorText.text = movie.message
            }
        }

    }


}