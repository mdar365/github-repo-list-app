package com.example.githubrepolistingapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson

object GithubService {
    const val REPO_LIST_URL = "https://api.github.com/users/mdar365/repos"
    fun GetRepoList(): String {
        return asyncGetHttpRequest(REPO_LIST_URL)
    }

    private fun asyncGetHttpRequest(endpoint: String): String {
        val url = URL(endpoint)
        val openedConnection = url.openConnection() as HttpURLConnection
        openedConnection.requestMethod = "GET"

        val responseCode = openedConnection.responseCode
        val reader = BufferedReader(InputStreamReader(openedConnection.inputStream))
        val responseText = try {
            reader.readText()
        } catch (e: Exception) {
            // Handle error cases and call the error callback on the main thread
            "Error"
        }

        reader.close()
        return responseText
    }

    private inline fun <reified T>parseJson(text: String): T =
        Gson().fromJson(text, T::class.java)

    data class ApiResponse<T>(
        val responseCode: Int,
        val response: T
    )
}
