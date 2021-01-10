package no3ratii.mohammad.dev.app.pagingtestnew.model.connection.api

import no3ratii.mohammad.dev.app.pagingtestnew.model.connection.response.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

/**
 * Github API communication setup via Retrofit.
 */
interface GithubService {
    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
            @Query("q") query: String,
            @Query("page") page: Int,
            @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse
}