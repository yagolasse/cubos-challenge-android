package cubos.challenge.movies.async

import android.graphics.Bitmap
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import cubos.challenge.movies.config.NULL_IMAGE_URL_HIGH_QUALITY
import cubos.challenge.movies.config.NULL_IMAGE_URL_MID_QUALITY
import cubos.challenge.movies.model.Movie

class DownloadPosterTask(
        private val poster: ImageView,
        private val progressBar: ProgressBar,
        private val movie: Movie
) : AsyncTask<String, Void, Bitmap>() {

    override fun onPreExecute() {
        poster.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg urls: String?): Bitmap? {
        val finalPosterURL = urls[0]
        var poster: Bitmap? = null

        if (finalPosterURL != null &&
                finalPosterURL != NULL_IMAGE_URL_MID_QUALITY &&
                finalPosterURL != NULL_IMAGE_URL_HIGH_QUALITY) {

            poster = Picasso.get().load(finalPosterURL).get()
        }
        return poster
    }

    override fun onPostExecute(result: Bitmap?) {
        poster.setImageBitmap(result)
        poster.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}