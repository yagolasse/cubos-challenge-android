package cubos.challenge.movies.factory

import android.os.Bundle
import cubos.challenge.movies.config.*
import cubos.challenge.movies.fragment.MovieListFragment

fun MovieListFragment(index: Int = -1): MovieListFragment {
    val fragment = MovieListFragment()

    if (index != -1) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_GENRE_KEY, convertToGenreId(index))
        fragment.arguments = bundle
    }

    return fragment
}

private fun convertToGenreId(index: Int) = when (index) {
    ACTION_TAB_INDEX -> ACTION_GENRE_ID
    DRAMA_TAB_INDEX -> DRAMA_GENRE_ID
    FANTASY_TAB_INDEX -> FANTASY_GENRE_ID
    FICTION_TAB_INDEX -> FICTION_GENRE_ID
    else -> throw IllegalArgumentException("This Fragment index does not exist")
}
