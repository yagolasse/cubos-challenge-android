package cubos.challenge.movies.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cubos.challenge.movies.R
import cubos.challenge.movies.activity.MovieDetailActivity
import cubos.challenge.movies.adapter.MovieAdapter
import cubos.challenge.movies.config.MOVIE_GENRE_KEY
import cubos.challenge.movies.config.MOVIE_ID_KEY
import cubos.challenge.movies.event.RecyclerItemClickListener
import cubos.challenge.movies.model.Movie
import cubos.challenge.movies.presenter.MainView
import cubos.challenge.movies.presenter.MovieListFragmentPresenterImpl
import cubos.challenge.movies.service.GetMovieInteractorImpl
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MovieListFragment : Fragment(), MainView, RecyclerItemClickListener {

    lateinit var presenter: MovieListFragmentPresenterImpl
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewLayoutManager = GridLayoutManager(activity, 2)
        presenter = MovieListFragmentPresenterImpl(this, GetMovieInteractorImpl())

        movieListRecyclerView.layoutManager = recyclerViewLayoutManager

        val movieGenre = arguments?.getInt(MOVIE_GENRE_KEY)

        if (arguments != null) presenter.requestMovieListByGenre(movieGenre!!)
    }

    override fun showProgress() {
        fragmentProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        fragmentProgressBar.visibility = View.GONE
    }

    override fun setDataToRecyclerView(movieList: Array<Movie>) {
        adapter = MovieAdapter(movieList, this)
        movieListRecyclerView.adapter = adapter
        fragmentMovieListImageViewEmpty.visibility = View.GONE
        fragmentMovieListTextViewEmpty.visibility = View.GONE
    }

    override fun onResponseFailure(throwable: Throwable) {
        fragmentMovieListImageViewEmpty.visibility = View.VISIBLE
        fragmentMovieListTextViewEmpty.visibility = View.VISIBLE
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID_KEY, movie.id)
        startActivity(intent)
    }

    override fun setMovieDetailData(movie: Movie) {
    }

}
