package com.example.reviewerpicture.presentation.di.fullImage

import com.example.reviewerpicture.presentation.fullImagePage.viewModel.FullImageVMF
import dagger.Module
import dagger.Provides

@Module
class FullImageModule {

    @FullImageScope
    @Provides
    fun provideFullImageVMF(): FullImageVMF {
        return FullImageVMF()
    }
}