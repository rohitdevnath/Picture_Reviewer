package com.example.reviewerpicture.presentation.listingPage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reviewerpicture.domain.usecase.GetAllDataUseCase
import com.example.reviewerpicture.domain.usecase.SubmitDataUseCase
import com.example.reviewerpicture.domain.usecase.UpdateDataUseCase
import io.reactivex.disposables.CompositeDisposable

class ListingVMF(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val submitDataUseCase: SubmitDataUseCase,
    private val compositeDisposable: CompositeDisposable
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ListingViewModel(getAllDataUseCase,updateDataUseCase,submitDataUseCase,compositeDisposable) as T
    }
}