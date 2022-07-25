package com.example.reviewerpicture.presentation.fullImagePage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FullImageVMF: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FullImageViewModel() as T
    }
}