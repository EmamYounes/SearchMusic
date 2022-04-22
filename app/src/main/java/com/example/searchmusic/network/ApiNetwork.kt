package com.example.searchmusic.network

import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resumeWithException

class ApiNetwork {


    suspend fun callGetTokenApi(accessToken: String): String {

        return suspendCancellableCoroutine { continuation ->
            try {
                val reader: BufferedReader
                val url = URL("http://staging-gateway.mondiamedia.com/v0/api/gateway/token/client")

                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"
                    reader = BufferedReader(InputStreamReader(inputStream) as Reader?)

                    val response = StringBuffer()
                    var inputLine = reader.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = reader.readLine()
                    }
                    reader.close()

                    if (continuation.isActive) {
                        continuation.resume(response.toString())
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                if (continuation.isActive) {
                    continuation.resumeWithException(e)
                }
            }
        }
    }
}

}