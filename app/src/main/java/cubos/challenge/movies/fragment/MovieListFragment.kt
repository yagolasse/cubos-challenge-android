package cubos.challenge.movies.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cubos.challenge.movies.R
import cubos.challenge.movies.activity.MovieDetailActivity
import cubos.challenge.movies.adapter.MovieAdapter
import cubos.challenge.movies.config.MOVIE_GENRE_KEY
import cubos.challenge.movies.config.MOVIE_ID_KEY
import cubos.challenge.movies.event.EndlessRecyclerViewScrollListener
import cubos.challenge.movies.event.RecyclerItemClickListener
import cubos.challenge.movies.model.Movie
import cubos.challenge.movies.presenter.MainView
import cubos.challenge.movies.presenter.MovieListFragmentPresenterImpl
import cubos.challenge.movies.service.GetMovieInteractorImpl
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MovieListFragment : Fragment(), MainView, RecyclerItemClickListener {

    lateinit var presenter: MovieListFragmentPresenterImpl
    var lastQuery: String? = null
    private lateinit var adapter: MovieAdapter
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewLayoutManager = GridLayoutManager(activity, 2)
        val movieGenre = arguments?.getInt(MOVIE_GENRE_KEY)

        presenter = MovieListFragmentPresenterImpl(this, GetMovieInteractorImpl())
        adapter = MovieAdapter(this)
        movieListRecyclerView.adapter = adapter
        movieListRecyclerView.layoutManager = recyclerViewLayoutManager

        scrollListener = object : EndlessRecyclerViewScrollListener(recyclerViewLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (arguments != null) presenter.requestMovieListByGenre(page, movieGenre!!)
                else presenter.requestMovieListByName(page, lastQuery!!)
            }
        }
        movieListRecyclerView.addOnScrollListener(scrollListener!!)

        if (arguments != null) presenter.requestMovieListByGenre(genreId = movieGenre!!)
    }

    override fun showProgress() {
        fragmentProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        fragmentProgressBar.visibility = View.GONE
    }

    override fun setDataToRecyclerView(movieList: Array<Movie>) {
        adapter.dataList =
                if(movieList.isNotEmpty()) adapter.dataList.plus(movieList)
                else arrayOf(/* nothing */)

        adapter.notifyDataSetChanged()
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
