package com.example.reviewerpicture.presentation.di.core

import android.content.Context
import com.example.reviewerpicture.presentation.di.listing.ListingSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ListingSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context.applicationContext
    }
}