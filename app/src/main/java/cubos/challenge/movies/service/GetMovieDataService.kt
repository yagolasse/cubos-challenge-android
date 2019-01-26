package cubos.challenge.movies.service

import cubos.challenge.movies.config.ADDITIONAL_URL_MOVIE_DETAIL
import cubos.challenge.movies.config.DISCOVER_URL
import cubos.challenge.movies.config.SEARCH_URL
import cubos.challenge.movies.model.Movie
import cubos.challenge.movies.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieDataService {

    @GET(ADDITIONAL_URL_MOVIE_DETAIL)
    fun getMovieById(@Path("id") movieId: Int): Call<Movie>

    @GET(DISCOVER_URL)
    fun getMoviesByGenre(@Query("with_genres") genreId: Int): Call<MovieList>

    @GET(SEARCH_URL)
    fun getMoviesByTitle(@Query("query") movieTitle: String): Call<MovieList>

}