package cubos.challenge.movies.presenter

import cubos.challenge.movies.model.Movie


class MovieListFragmentPresenterImpl(
        private var mainView: MainView,
        private val getMovieInteractor: GetMovieInteractor
) :
        Presenter, GetMovieInteractor.OnFinishedListener {

    override fun requestMovieListByName(page: Int, movieName: String) {
        mainView.showProgress()
        getMovieInteractor.getMovieArray(page = page, movieName = movieName, onFinishedListener = this)
    }

    override fun requestMovieListByGenre(page: Int, genreId: Int) {
        mainView.showProgress()
        getMovieInteractor.getMovieArray(page = page, genreId = genreId, onFinishedListener = this)
    }

    override fun onFinished(movieList: Array<Movie>) {
        sendDataToFragment(movieList)
    }

    override fun onFailure(t: Throwable) {
        mainView.onResponseFailure(t)
        mainView.hideProgress()
    }

    fun showEmptyMovieList() {
        sendDataToFragment(arrayOf(/* nothing */))
    }

    private fun sendDataToFragment(movieList: Array<Movie>) {
        mainView.setDataToRecyclerView(movieList)
        mainView.hideProgress()
    }

    override fun requestMovieById(movieId: Int) {}

    override fun onFinished(movie: Movie) {}

    override fun onDestroy() {}
}