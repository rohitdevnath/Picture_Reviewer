package com.example.reviewerpicture.presentation.di.listing

import com.example.reviewerpicture.domain.usecase.GetAllDataUseCase
import com.example.reviewerpicture.domain.usecase.SubmitDataUseCase
import com.example.reviewerpicture.domain.usecase.UpdateDataUseCase
import com.example.reviewerpicture.presentation.listingPage.viewModel.ListingVMF
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ListingModule {

    @ListingScope
    @Provides
    fun provideListingVMF(
        getAllDataUseCase: GetAllDataUseCase,
        updateDataUseCase: UpdateDataUseCase,
        submitDataUseCase: SubmitDataUseCase,
        compositeDisposable: CompositeDisposable
    ): ListingVMF {
        return ListingVMF(
            getAllDataUseCase = getAllDataUseCase,
            updateDataUseCase= updateDataUseCase,
            submitDataUseCase = submitDataUseCase,
            compositeDisposable = compositeDisposable
        )
    }

    @ListingScope
    @Provides
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
}