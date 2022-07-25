package com.example.reviewerpicture.presentation.di.core

import com.example.reviewerpicture.presentation.di.fullImage.FullImageSubComponent
import com.example.reviewerpicture.presentation.di.listing.ListingSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RemoteDataModule::class,
    CacheDataModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface AppComponent {
    fun listingSubComponent(): ListingSubComponent.Factory
    fun fullImageSubComponent(): FullImageSubComponent.Factory
}