package com.example.reviewerpicture.presentation.di.listing

import com.example.reviewerpicture.presentation.listingPage.view.ListingFragment
import dagger.Subcomponent

@ListingScope
@Subcomponent(modules = [ListingModule::class])
interface ListingSubComponent {
    fun inject(listingFragment: ListingFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): ListingSubComponent
    }
}