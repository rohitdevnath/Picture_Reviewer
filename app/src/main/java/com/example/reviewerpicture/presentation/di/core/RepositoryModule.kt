package com.example.reviewerpicture.presentation.di.core

import com.example.reviewerpicture.data.repository.*
import com.example.reviewerpicture.data.repository.dataSource.CacheDataSource
import com.example.reviewerpicture.data.repository.dataSource.RemoteDataSource
import com.example.reviewerpicture.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryImpl(remoteDataSource: RemoteDataSource,
                              cacheDataSource: CacheDataSource
    ): Repository {
        return AllDataRepositoryImpl(
            remoteDataSource = remoteDataSource,
            cacheDataSource= cacheDataSource
        )
    }
}