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
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.adapter.TrendingMovieListAdapter.MovieListViewHolder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view.OnMovieClickListener

class TrendingMovieListAdapter : ListAdapter<Movie, MovieListViewHolder>(MoviesComparator()) {

    private var movieList: MutableList<Movie> = mutableListOf()
    private lateinit var listener: OnMovieClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListViewHolder {
        return MovieListViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: MovieListViewHolder,
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

    fun addMovieToList(projList: MutableList<Movie>) {
        movieList.addAll(projList)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnMovieClickListener) {
        this.listener = listener
    }

    fun getMovieList(): MutableList<Movie> {
        return movieList
    }

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieId:MaterialCardView = itemView.findViewById(R.id.trending_movie_card)
        private var moviePoster: ImageView = itemView.findViewById(R.id.trending_movie_poster)
        private var movieTitle: TextView = itemView.findViewById(R.id.trending_movie_title)
        private var movieRating: RatingBar = itemView.findViewById(R.id.trending_movie_rating)

        fun bind(movie: Movie) {
            movieId.tag = movie.id
            if (movie.poster_path !== null) {
                DownloadImageTask(moviePoster).execute(Constant.Api.BASE_POSTER_URL + "w185" + movie.poster_path)
            }
            movieTitle.text = movie.title
            movieRating.rating = (movie.vote_average/2).toFloat()
        }

        companion object {
            fun create(parent: ViewGroup): MovieListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.trending_movie_list_item, parent, false)
                return MovieListViewHolder(view)
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