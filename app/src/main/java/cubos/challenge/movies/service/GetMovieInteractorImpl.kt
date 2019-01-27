package cubos.challenge.movies.service

import android.util.Log
import cubos.challenge.movies.model.Movie
import cubos.challenge.movies.model.MovieList
import cubos.challenge.movies.presenter.GetMovieInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMovieInteractorImpl : GetMovieInteractor {

    override fun getMovieById(movieId: Int, onFinishedListener: GetMovieInteractor.OnFinishedListener) {
        val service = RetrofitInstance.instance.create(GetMovieDataService::class.java)
        val call = service.getMovieById(movieId)


        Log.wtf("URL Called", call.request().url().toString())
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                onFinishedListener.onFinished(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }

    override fun getMovieArray(page: Int, genreId: Int, movieName: String, onFinishedListener: GetMovieInteractor.OnFinishedListener) {

        val service = RetrofitInstance.instance.create(GetMovieDataService::class.java)
        val call =
                if (genreId != 0) service.getMoviesByGenre(page, genreId)
                else service.getMoviesByTitle(page, movieName)

        call.enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                onFinishedListener.onFinished(response.body().movieList)
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }

}