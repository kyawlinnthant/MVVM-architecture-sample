package com.klt.gbs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.klt.gbs.databinding.LayoutListBinding
import com.klt.gbs.model.Movie
import timber.log.Timber

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = LayoutListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MovieViewHolder(private val binding: LayoutListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie?) {
            with(item) {
                binding.title.text = this!!.original_title
                binding.date.text = this.release_date
                Glide.with(this@MovieViewHolder.itemView.context)
                    .load("https://image.tmdb.org/t/p/original/"+this.backdrop_path)
                    .into(binding.img)
            }
            itemView.setOnClickListener {
                Timber.tag("itemclick: ").d(item.toString())
            }
        }
    }
}


