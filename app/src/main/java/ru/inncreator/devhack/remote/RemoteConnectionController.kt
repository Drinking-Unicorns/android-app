package ru.inncreator.devhack.remote

import android.content.Context
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import timber.log.Timber
import java.io.UnsupportedEncodingException

object RemoteConnectionController {


    // Instantiate the RequestQueue.
    lateinit var queue: RequestQueue

    fun initQueue(ctx: Context) {
        queue = Volley.newRequestQueue(ctx)
    }

    // Request a string response from the provided URL.
    private suspend fun checkConnect(): Boolean {
        val url = "http://94.26.248.9/api/Test/huy1"
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                Timber.i("Response is: $response")
            },
            {
                Timber.e(it, " Пошло по пизде")
            })
        queue.add(request)
        return false
    }

    // Request a string response from the provided URL.
    fun getAllEvents(onSuccess: (response: String) -> Unit) {
        val url = "http://94.26.248.9/events/Info/get-all-events"
        val request = StringRequest(
            Request.Method.GET, url,
            onSuccess,
            {
                Timber.e(it, " Пошло по пизде")
            })
        queue.add(request)
    }


    private fun postRequest(textView: TextView): StringRequest {
        val url = "http://94.26.248.9/api/Test/huy3/add"
        val jsonBody = JSONObject()
        jsonBody.put("id", "228")
        val requestBody = jsonBody.toString()
        // Request a string response from the provided URL.
        return object : StringRequest(
            Method.POST, url, {
                textView.text = "String Response"
                Timber.i("Норм $it")
            }, {
                textView.text = "Error getting response"
                Timber.e(it, "Тоже пошло по пизде")
            }) {
            override fun getBody(): ByteArray? {
                return try {
                    if (requestBody == null) null else requestBody.encodeToByteArray()
                } catch (uee: UnsupportedEncodingException) {
                    VolleyLog.wtf(
                        "Unsupported Encoding while trying to get the bytes of %s using %s",
                        requestBody,
                        "utf-8"
                    )
                    null
                }
            }

            override fun getHeaders(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["Content-Type"] = "application/json"
                return map
            }
        }
    }
}