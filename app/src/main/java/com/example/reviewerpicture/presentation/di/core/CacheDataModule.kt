package com.example.reviewerpicture.presentation.di.core

import com.example.reviewerpicture.data.repository.CacheDataSource
import com.example.reviewerpicture.data.repository.CacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideCacheData(): CacheDataSource {
        return CacheDataSourceImpl()
    }
}