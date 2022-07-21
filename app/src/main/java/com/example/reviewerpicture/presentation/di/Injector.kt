package com.example.reviewerpicture.presentation.di

import com.example.reviewerpicture.presentation.di.listing.ListingSubComponent

interface Injector {
    fun createListingSubComponent(): ListingSubComponent
}