package cubos.challenge.movies.presenter

import cubos.challenge.movies.model.Movie

class MovieDetailActivityPresenterImpl(
        private var mainView: MainView,
        private val getMovieInteractor: GetMovieInteractor
) :
        Presenter, GetMovieInteractor.OnFinishedListener {

    override fun requestMovieById(movieId: Int) {
        mainView.showProgress()
        getMovieInteractor.getMovieById(movieId, this)
    }

    override fun onFinished(movieList: Array<Movie>) {}

    override fun onFinished(movie: Movie) {
        mainView.setMovieDetailData(movie)
        mainView.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        mainView.onResponseFailure(t)
    }

    override fun onDestroy() {}

    override fun requestMovieListByGenre(page: Int, genreId: Int) { }

    override fun requestMovieListByName(page: Int, movieName: String) { }

}