package com.klt.gbs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.klt.gbs.databinding.LayoutListBinding
import com.klt.gbs.model.Movie

class MovieListAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<Movie, RecyclerView.ViewHolder>(newsDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieListViewHolder).bind(getItem(position))
    }

    companion object {
        val newsDiffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getClickItem(position: Int): Movie = getItem(position)

    inner class MovieListViewHolder(private val binding: LayoutListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie?) {
            with(item) {
                binding.title.text = this!!.originalTitle
                binding.date.text = this.releaseDate
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/" + this.path)
                    .into(binding.img)
            }
            itemView.setOnClickListener { onClick(adapterPosition) }
        }
    }
}