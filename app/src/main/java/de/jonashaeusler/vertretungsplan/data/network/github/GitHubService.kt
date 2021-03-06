package de.jonashaeusler.vertretungsplan.data.network.github

import de.jonashaeusler.vertretungsplan.data.entities.GitHubRelease
import de.jonashaeusler.vertretungsplan.data.network.GITHUB_BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface GitHubService {
    @GET("/repositories/108325506/releases/latest")
    fun getLatestVersion(): Call<GitHubRelease>

    companion object {
        fun create(): GitHubService {
            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(GITHUB_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            return retrofit.create(GitHubService::class.java)
        }
    }
}
