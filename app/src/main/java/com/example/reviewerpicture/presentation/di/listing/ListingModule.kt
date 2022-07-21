package com.example.reviewerpicture.presentation.di.listing

import com.example.reviewerpicture.data.repository.AllDataRepositoryImpl
import com.example.reviewerpicture.data.repository.CacheDataSourceImpl
import com.example.reviewerpicture.data.repository.RemoteDataSourceImpl
import com.example.reviewerpicture.domain.usecase.GetAllDataUseCase
import com.example.reviewerpicture.domain.usecase.SubmitDataUseCase
import com.example.reviewerpicture.domain.usecase.UpdateDataUseCase
import com.example.reviewerpicture.presentation.listingPage.viewModel.ListingVMF
import dagger.Module
import dagger.Provides

@Module
class ListingModule {

    @ListingScope
    @Provides
    fun provideListingVMF(
        getAllDataUseCase: GetAllDataUseCase,
        updateDataUseCase: UpdateDataUseCase,
        submitDataUseCase: SubmitDataUseCase
    ): ListingVMF {
        return ListingVMF(
            getAllDataUseCase = getAllDataUseCase,
            updateDataUseCase= updateDataUseCase,
            submitDataUseCase = submitDataUseCase
        )
    }
}