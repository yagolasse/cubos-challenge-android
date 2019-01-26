package cubos.challenge.movies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import cubos.challenge.movies.config.BASE_IMAGE_URL
import cubos.challenge.movies.config.IMAGE_QUALITY_MID
import cubos.challenge.movies.R
import cubos.challenge.movies.async.DownloadPosterTask
import cubos.challenge.movies.event.RecyclerItemClickListener
import cubos.challenge.movies.model.Movie


class MovieAdapter(
        private val dataList: Array<Movie>,
        private val recyclerItemClickListener: RecyclerItemClickListener
) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = dataList[position]
        val downloadPosterTask = DownloadPosterTask(holder.poster, holder.progressBar, movie)
        val finalPosterURL = BASE_IMAGE_URL.plus(IMAGE_QUALITY_MID).plus(movie.posterPath)

        holder.title.text = movie.title
        holder.itemView.setOnClickListener { recyclerItemClickListener.onItemClick(movie) }

        downloadPosterTask.execute(finalPosterURL)
    }

    override fun getItemCount() = dataList.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var title: TextView = itemView.findViewById(R.id.movieListItemTitleTextView)
        internal var poster: ImageView = itemView.findViewById(R.id.movieListItemPosterImageView)
        internal var progressBar: ProgressBar = itemView.findViewById(R.id.movieListItemImageProgressBar)

    }
}