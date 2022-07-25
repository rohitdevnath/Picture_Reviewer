package com.example.reviewerpicture.presentation.di

import com.example.reviewerpicture.presentation.di.fullImage.FullImageSubComponent
import com.example.reviewerpicture.presentation.di.listing.ListingSubComponent

interface Injector {
    fun createListingSubComponent(): ListingSubComponent
    fun createFullImageSubComponent(): FullImageSubComponent
}