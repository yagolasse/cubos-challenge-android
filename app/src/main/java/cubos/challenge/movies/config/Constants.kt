package cubos.challenge.movies.config

// General Definitions
const val CURRENT_TAB_COUNT = 4
const val MOVIE_GENRE_KEY = "MOVIE_GENRE_KEY"
const val MOVIE_ID_KEY = "MOVIE_ID_KEY"

// Tab Fragments Index
const val ACTION_TAB_INDEX = 0
const val DRAMA_TAB_INDEX = 1
const val FANTASY_TAB_INDEX = 2
const val FICTION_TAB_INDEX = 3

// Retrofit Data
const val API_KEY = "21ab19da35c8481721c284f94bd95e49"
const val ADDITIONAL_URL = "movie?api_key=$API_KEY&language=pt-BR&sort_by=popularity.desc&include_adult=false&include_video=false"
const val ADDITIONAL_URL_MOVIE_DETAIL = "movie/{id}?api_key=$API_KEY&language=pt-BR"
const val DISCOVER_URL = "discover/$ADDITIONAL_URL"
const val SEARCH_URL = "search/$ADDITIONAL_URL"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_QUALITY_MID = "w500/"
const val IMAGE_QUALITY_HIGH = "w342/"
const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
const val NULL_IMAGE_URL_MID_QUALITY = "$BASE_IMAGE_URL${IMAGE_QUALITY_MID}null"
const val NULL_IMAGE_URL_HIGH_QUALITY = "$BASE_IMAGE_URL${IMAGE_QUALITY_HIGH}null"

// Movies Genre ID
const val ACTION_GENRE_ID = 28
const val DRAMA_GENRE_ID = 18
const val FANTASY_GENRE_ID = 14
const val FICTION_GENRE_ID = 878