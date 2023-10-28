package com.example.githubrepolistingapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object GithubApiBuilder {
    private val spec: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
        .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
        .cipherSuites(
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA)
        .build()
    val okttp: OkHttpClient.Builder = OkHttpClient.Builder()
        .connectionSpecs(Collections.singletonList(spec))
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okttp.build())
        .build()

    var service: GitHubService = retrofit.create(GitHubService::class.java)
    var repoResults: MutableList<Repo> by mutableStateOf(mutableListOf())


    fun getRepos(gitUser: String) {
        try {
            val repos: Call<MutableList<Repo?>?>? = service.listRepos(gitUser)
            val response = repos?.execute();
            println("Response code ${response?.code()}")
            response?.body()?.let {
                repoResults = it as MutableList<Repo>
                println(repoResults)
            };
        } catch (e: Exception) {
            println("ERROR IN GET REPO: ${e.toString()}")
        }
    }
}