package cubos.challenge.movies.presenter

import cubos.challenge.movies.model.Movie


interface Presenter {

    fun onDestroy()

    fun requestMovieListByName(page: Int = 1, movieName: String = "")

    fun requestMovieListByGenre(page: Int = 1, genreId: Int = 0)

    fun requestMovieById(movieId: Int)

}

interface MainView {

    fun showProgress()

    fun hideProgress()

    fun setDataToRecyclerView(movieList: Array<Movie>)

    fun setMovieDetailData(movie: Movie)

    fun onResponseFailure(throwable: Throwable)

}

interface GetMovieInteractor {

    interface OnFinishedListener {
        fun onFinished(movieList: Array<Movie>)
        fun onFinished(movie: Movie)
        fun onFailure(t: Throwable)
    }

    fun getMovieById(movieId: Int, onFinishedListener: GetMovieInteractor.OnFinishedListener)

    fun getMovieArray(page: Int, genreId: Int = 0, movieName: String = "", onFinishedListener: OnFinishedListener)
}
