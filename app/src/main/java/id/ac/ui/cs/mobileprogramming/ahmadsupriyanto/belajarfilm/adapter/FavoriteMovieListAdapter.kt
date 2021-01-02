package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.Constant
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.DownloadImageTask
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.adapter.FavoriteMovieListAdapter.FavoriteMovieListViewHolder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.FavoriteMovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view.FavoriteListFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view.OnFavoriteMovieClickListener

class FavoriteMovieListAdapter : ListAdapter<Movie, FavoriteMovieListViewHolder>(MoviesComparator()) {

    private var movieList: MutableList<FavoriteMovieDb> = mutableListOf()
    private lateinit var listener: OnFavoriteMovieClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMovieListViewHolder {
        return FavoriteMovieListViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: FavoriteMovieListViewHolder,
        position: Int
    ) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener.onMovieClick(it, movieList)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun addMovieToList(projList: MutableList<FavoriteMovieDb>) {
        movieList.addAll(projList)
        notifyDataSetChanged()
    }

    fun setListener(listener: FavoriteListFragment) {
        this.listener = listener
    }

    fun getMovieList(): MutableList<FavoriteMovieDb> {
        return movieList
    }

    class FavoriteMovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieId:MaterialCardView = itemView.findViewById(R.id.favorite_movie_card)
        private var moviePoster: ImageView = itemView.findViewById(R.id.favorite_movie_poster)
        private var movieTitle: TextView = itemView.findViewById(R.id.favorite_movie_title)
        private var movieRating: RatingBar = itemView.findViewById(R.id.favorite_movie_rating)

        fun bind(movie: FavoriteMovieDb) {
            movieId.tag = movie.id
            if (movie.posterPath !== null) {
                DownloadImageTask(moviePoster).execute(Constant.Api.BASE_POSTER_URL + "w185" + movie.posterPath)
            }
            movieTitle.text = movie.title
            movieRating.rating = (movie.voteAverage?.div(2))?.toFloat() ?: 0F
        }

        companion object {
            fun create(parent: ViewGroup): FavoriteMovieListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.favorite_movie_list_item, parent, false)
                return FavoriteMovieListViewHolder(view)
            }
        }
    }

    class MoviesComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

}