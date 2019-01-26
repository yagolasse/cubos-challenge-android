package cubos.challenge.movies.async

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import cubos.challenge.movies.R
import cubos.challenge.movies.config.NULL_IMAGE_URL_HIGH_QUALITY
import cubos.challenge.movies.config.NULL_IMAGE_URL_MID_QUALITY


class DownloadPosterTask(
        private val poster: ImageView,
        private val progressBar: ProgressBar
) : AsyncTask<String, Void, Bitmap>() {

    override fun onPreExecute() {
        poster.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg urls: String?): Bitmap? {
        val finalPosterURL = urls[0]

        return if (finalPosterURL != null &&
                finalPosterURL != NULL_IMAGE_URL_MID_QUALITY &&
                finalPosterURL != NULL_IMAGE_URL_HIGH_QUALITY) {
            try {
                Picasso.get().load(finalPosterURL).get()
            } catch (e: Exception) {
                BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.baseline_cloud_off_black_18dp)
            }
        } else {
            BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.baseline_portrait_black_24dp)
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        poster.setImageBitmap(result)
        poster.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}