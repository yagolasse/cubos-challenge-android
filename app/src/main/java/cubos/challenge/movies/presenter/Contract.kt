package cubos.challenge.movies.presenter

import cubos.challenge.movies.model.Movie


interface Presenter {

    fun onDestroy()

    fun requestMovieListByName(movieName: String = "")

    fun requestMovieListByGenre(genreId: Int = 0)

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

    fun getMovieArray(genreId: Int = 0, movieName: String = "", onFinishedListener: OnFinishedListener)
}
