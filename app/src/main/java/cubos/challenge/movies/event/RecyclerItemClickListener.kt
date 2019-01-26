package cubos.challenge.movies.event

import cubos.challenge.movies.model.Movie

interface RecyclerItemClickListener {
    fun onItemClick(movie: Movie)
}