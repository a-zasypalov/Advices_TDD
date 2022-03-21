package com.gaoyun.advices.di

import com.gaoyun.advices.data.remote.AdvicesSlipApiClient
import com.gaoyun.advices.data.repository.AdviceRepositoryImpl
import com.gaoyun.advices.domain.repository.AdviceRepository
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCase
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCaseImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdviceModule {

    private const val ADVICE_SLIP_BASE_URL = "https://api.adviceslip.com"

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(ADVICE_SLIP_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient)
            .build()

    @Singleton
    @Provides
    fun provideAdvicesSlipClient(retrofit: Retrofit): AdvicesSlipApiClient = retrofit.create(AdvicesSlipApiClient::class.java)

    @Singleton
    @Provides
    fun provideAdviceRepository(apiClient: AdvicesSlipApiClient): AdviceRepository = AdviceRepositoryImpl(apiClient)

}