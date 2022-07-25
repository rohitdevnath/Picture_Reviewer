package com.example.reviewerpicture.presentation.di.fullImage

import com.example.reviewerpicture.presentation.fullImagePage.view.FullImageFragment
import dagger.Subcomponent

@FullImageScope
@Subcomponent(modules = [FullImageModule::class])
interface FullImageSubComponent {
    fun inject(fragment: FullImageFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): FullImageSubComponent
    }
}