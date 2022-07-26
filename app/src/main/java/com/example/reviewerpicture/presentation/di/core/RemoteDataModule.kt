package com.example.reviewerpicture.presentation.di.core

import android.content.Context
import com.example.reviewerpicture.data.repository.dataSource.RemoteDataSource
import com.example.reviewerpicture.data.repository.dataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRemoteData(context: Context): RemoteDataSource {
        return RemoteDataSourceImpl(context = context)
    }
}