package it.to.data.inject

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.to.data.api.PiratesApi
import it.to.data.api.adapter.LocalDateTimeAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Module that handle network client dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(localDateTimeAdapter: LocalDateTimeAdapter): Gson =
        GsonBuilder().serializeNulls()
            .registerTypeAdapter(LocalDateTime::class.java, localDateTimeAdapter)
            .create()

    @Provides
    @Singleton
    fun providedRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(HTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(HTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)

    @Provides
    @Singleton
    fun provideHttpClient(
        builder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = builder
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun piratesApi(retrofit: Retrofit): PiratesApi = retrofit.create(PiratesApi::class.java)

    companion object {
        /**
         * Timeout value for the http client in millis
         */
        const val HTTP_CLIENT_TIMEOUT = 20L

        /**
         * Base Url related to this app
         */
        const val BASE_URL = "https://assets.shpock.com/mobile/interview-test/"
    }
}
