package com.example.reviewerpicture.presentation.di.core

import com.example.reviewerpicture.domain.repository.Repository
import com.example.reviewerpicture.domain.usecase.GetAllDataUseCase
import com.example.reviewerpicture.domain.usecase.SubmitDataUseCase
import com.example.reviewerpicture.domain.usecase.UpdateDataUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllDataUseCase(repository: Repository): GetAllDataUseCase
    {
        return GetAllDataUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideSubmitDataUseCase(repository: Repository): SubmitDataUseCase
    {
        return SubmitDataUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideUpdateDataUseCase(repository: Repository): UpdateDataUseCase
    {
        return UpdateDataUseCase(repository = repository)
    }
}