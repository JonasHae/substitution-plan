package de.jonashaeusler.vertretungsplan.data.network.api

import android.os.AsyncTask
import com.github.kevinsawicki.http.HttpRequest
import de.jonashaeusler.vertretungsplan.data.network.VERTRETUNGSBOT_BASE_URL

class InfoTask(private val callback: OnInfoResolved? = null) : AsyncTask<String, Long, String>() {
    override fun doInBackground(vararg p0: String?): String {
        return try {
            HttpRequest.get("$VERTRETUNGSBOT_BASE_URL/api/infos.php").body()
        } catch (e: HttpRequest.HttpRequestException) {
            e.printStackTrace()
            ""
        }
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        callback?.onInfoResolved(result)
    }
}
