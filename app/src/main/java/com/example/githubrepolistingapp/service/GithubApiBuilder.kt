package com.example.githubrepolistingapp.service

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.githubrepolistingapp.model.Repo
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

object GithubApiBuilder {
    const val DEFAULT_ERROR_MSG = "There was an error"

    var repoResults: List<Repo> by mutableStateOf(listOf())
    var errorMsg: String? by mutableStateOf(null)

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: GitHubService = retrofit.create(GitHubService::class.java)

    fun getRepos(gitUser: String) {
        try {
            val repos: Call<MutableList<Repo>> = service.listRepos(gitUser)
            val response = repos.execute()

            println("Response code ${response.code()}")

            if (!checkResponseIsSuccessful(response)) return
            response.body()?.let {
                repoResults = it
                println(repoResults)
            }
        } catch (e: Exception) {
            errorMsg = DEFAULT_ERROR_MSG
            repoResults = listOf()
            println("Error when retrieving repositories: $e")
        }
    }

    private fun checkResponseIsSuccessful(response: Response<MutableList<Repo>>): Boolean {
        errorMsg = when {
            response.body()?.isEmpty() == true -> "User has no repositories"
            response.isSuccessful -> null
            response.code() == HttpURLConnection.HTTP_NOT_FOUND -> "User not found"
            else -> DEFAULT_ERROR_MSG
        }

        if (errorMsg != null) repoResults = listOf()
        return errorMsg == null
    }
}