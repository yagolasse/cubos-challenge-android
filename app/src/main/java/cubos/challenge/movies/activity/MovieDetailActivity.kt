package cubos.challenge.movies.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cubos.challenge.movies.R
import cubos.challenge.movies.async.DownloadPosterTask
import cubos.challenge.movies.config.BASE_IMAGE_URL
import cubos.challenge.movies.config.IMAGE_QUALITY_HIGH
import cubos.challenge.movies.config.MOVIE_ID_KEY
import cubos.challenge.movies.model.Movie
import cubos.challenge.movies.presenter.MainView
import cubos.challenge.movies.presenter.MovieDetailActivityPresenterImpl
import cubos.challenge.movies.service.GetMovieInteractorImpl
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieId = intent.getIntExtra(MOVIE_ID_KEY, 0)
        MovieDetailActivityPresenterImpl(this, GetMovieInteractorImpl()).requestMovieById(movieId)
    }

    override fun setMovieDetailData(movie: Movie) {
        val downloadPosterTask = DownloadPosterTask(activityDetailPosterImageView, activityDetailImageProgressBar)
        val finalPosterURL = BASE_IMAGE_URL.plus(IMAGE_QUALITY_HIGH).plus(movie.posterPath)
        title = movie.title
        activityDetailDescriptionTextView.text = movie.overview
        downloadPosterTask.execute(finalPosterURL)
        activityDetailImageViewEmpty.visibility = View.GONE
        activityDetailTextViewEmpty.visibility = View.GONE
    }

    override fun showProgress() {
        activityDetailProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        activityDetailProgressBar.visibility = View.GONE
        activityDetailPosterImageView.visibility = View.VISIBLE
    }

    override fun setDataToRecyclerView(movieList: Array<Movie>) {}

    override fun onResponseFailure(throwable: Throwable) {
        activityDetailImageViewEmpty.visibility = View.VISIBLE
        activityDetailTextViewEmpty.visibility = View.VISIBLE
        activityDetailProgressBar.visibility = View.GONE
        activityDetailDataLayout.visibility = View.GONE
    }

}
