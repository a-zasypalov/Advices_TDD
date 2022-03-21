package com.gaoyun.advices.di

import com.gaoyun.advices.domain.repository.AdviceRepository
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCase
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetRandomAdviceUseCase(repository: AdviceRepository): GetRandomAdviceUseCase = GetRandomAdviceUseCaseImpl(repository)

}