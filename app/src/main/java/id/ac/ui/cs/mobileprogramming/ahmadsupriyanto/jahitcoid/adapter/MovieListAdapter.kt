package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Constant
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.DownloadImageTask
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.MovieListAdapter.MovieListViewHolder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.MovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view.OnMovieClickListener

class MovieListAdapter : ListAdapter<MovieDb, MovieListViewHolder>(MoviesComparator()) {

    private var movieList: MutableList<MovieDb> = mutableListOf()
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
                listener.onMovieClick(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun addMovieToList(projList: MutableList<MovieDb>) {
        movieList.addAll(projList)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnMovieClickListener) {
        this.listener = listener
    }

    fun getMovieList(): MutableList<MovieDb> {
        return movieList
    }

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieId:MaterialCardView = itemView.findViewById(R.id.movie_card)
        private var moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
        private var movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        private var movieDescription: TextView = itemView.findViewById(R.id.movie_description)
//        private var movieStatus: TextView = itemView.findViewById(R.id.movie_status)
//        private var movieAnnotation: TextView = itemView.findViewById(R.id.movie_annotation)

        fun bind(movie: MovieDb) {
            movieId.tag = movie.id
            if (movie.poster_path !== null) {
                DownloadImageTask(moviePoster).execute(Constant.Api.BASE_POSTER_URL + "w185" + movie.poster_path)
                Log.d("test", Constant.Api.BASE_POSTER_URL + "w185" + movie.poster_path)
            }
            movieTitle.text = movie.title;
            movieDescription.text = movie.overview.substring(0, 80) + "...";
//            movieAmount.text = movie.amount;
//            movieAnnotation.text = movie.annotation;
//            movieStatus.text = movie.status;
        }

        companion object {
            fun create(parent: ViewGroup): MovieListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_list_item, parent, false)
                return MovieListViewHolder(view)
            }
        }
    }

    class MoviesComparator : DiffUtil.ItemCallback<MovieDb>() {
        override fun areItemsTheSame(oldItem: MovieDb, newItem: MovieDb): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieDb, newItem: MovieDb): Boolean {
            return oldItem.id == newItem.id
        }
    }

}