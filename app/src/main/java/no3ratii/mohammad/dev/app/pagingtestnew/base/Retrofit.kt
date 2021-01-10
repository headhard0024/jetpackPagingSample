package no3ratii.mohammad.dev.app.pagingtestnew.base

import no3ratii.mohammad.dev.app.pagingtestnew.model.connection.api.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//connction to server
class Retrofit {
    companion object {
        private const val BASE_URL = "https://api.github.com/"

        private fun ServiceBuilder(): Retrofit {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        //use for set new instans form service
        fun create(): GithubService {
            return ServiceBuilder().create(GithubService::class.java)
        }
    }
}