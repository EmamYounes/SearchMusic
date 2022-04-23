package com.example.instabugandroidchallenge.network

import android.text.Html
import org.json.JSONObject
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL


class HttpRequest {

    companion object {

        fun callGetTokenApi(): String {
            var htmlText = ""
            val url = URL("http://staging-gateway.mondiamedia.com/v0/api/gateway/token/client")
            val urlConnection = url.openConnection() as HttpURLConnection
            with(urlConnection) {
                requestMethod = "POST"
                setRequestProperty("Accept", "application/json")
                doOutput = true
                doInput = true
                readTimeout = 120000
                connectTimeout = 120000
                val postDataParams = JSONObject()
                postDataParams.put("api_key", "G2269608a-bf41-2dc7-cfea-856957fcab1e")
                val dataOutputStream = DataOutputStream(outputStream)
                dataOutputStream.writeBytes(postDataParams.toString())
                dataOutputStream.flush()
                dataOutputStream.close()
            }
            try {
                htmlText =
                    Html.fromHtml(
                        urlConnection.inputStream.bufferedReader().readText(),
                        Html.FROM_HTML_MODE_COMPACT
                    ).toString()

                return htmlText
            } finally {
                urlConnection.disconnect()
            }
        }


    }

}

fun String.onlyLetters() = all { it.isLetter() }
