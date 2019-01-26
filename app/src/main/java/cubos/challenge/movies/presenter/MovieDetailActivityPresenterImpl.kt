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

    override fun onFailure(t: Throwable) { }

    override fun onDestroy() { }

    override fun requestMovieListByName(movieName: String) { }

    override fun requestMovieListByGenre(genreId: Int) { }

}