package cubos.challenge.movies.model

import com.google.gson.annotations.SerializedName

class MovieList(@SerializedName("results") val movieList: Array<Movie>)