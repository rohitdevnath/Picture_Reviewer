package com.example.reviewerpicture.presentation.di.core

import com.example.reviewerpicture.data.repository.AllDataRepositoryImpl
import com.example.reviewerpicture.data.repository.CacheDataSourceImpl
import com.example.reviewerpicture.data.repository.RemoteDataSourceImpl
import com.example.reviewerpicture.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryImpl(remoteDataSourceImpl: RemoteDataSourceImpl,
                              cacheDataSourceImpl: CacheDataSourceImpl
    ): Repository {
        return AllDataRepositoryImpl(
            remoteDataSourceImpl = remoteDataSourceImpl,
            cacheDataSourceImpl= cacheDataSourceImpl
        )
    }
}