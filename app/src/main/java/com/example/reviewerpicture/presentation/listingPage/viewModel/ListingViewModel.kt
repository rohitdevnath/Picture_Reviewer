package com.example.reviewerpicture.presentation.listingPage.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.data.model.uiModel.CommentDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ImageDataUiModel
import com.example.reviewerpicture.data.model.uiModel.SingleOptionUiModel
import com.example.reviewerpicture.domain.usecase.GetAllDataUseCase
import com.example.reviewerpicture.domain.usecase.SubmitDataUseCase
import com.example.reviewerpicture.domain.usecase.UpdateDataUseCase
import com.example.reviewerpicture.utils.addTo
import com.example.reviewerpicture.utils.networkUtils.Outcome
import com.example.reviewerpicture.utils.performOnBackOutOnMain
import io.reactivex.disposables.CompositeDisposable

class ListingViewModel(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val submitDataUseCase: SubmitDataUseCase,
    private val compositeDisposable: CompositeDisposable
    ): ViewModel() {

    val allData: MutableLiveData<Outcome<List<AllDataUiModel>>> = MutableLiveData()

    val submitData: MutableLiveData<Outcome<String>> = MutableLiveData()

    var currentImageUri: Uri? = null

    var currentImageDataUiModel: ImageDataUiModel? = null

    fun getAllData(forceRemote: Boolean) {
        getAllDataUseCase.execute(forceRemote)
            .performOnBackOutOnMain()
            .subscribe (
                {
                    allData.value = Outcome.success(it)
                },{
                    allData.value = Outcome.failure(it)
                }
            )
            .addTo(compositeDisposable)
    }

    fun submitResponse(){
        submitData.value = Outcome.loading()
        submitDataUseCase.execute()
            .performOnBackOutOnMain()
            .subscribe (
                {
                    submitData.value = Outcome.success("Response Submitted")
                },{
                   submitData.value = Outcome.failure(it)
                }
            )
            .addTo(compositeDisposable)
    }

    fun updateOption(optionUiModel: SingleOptionUiModel){
        allData.value = Outcome.success(updateDataUseCase.updateOption(optionUiModel))
    }

    fun updateCommentText(commentUiModel: CommentDataUiModel){
        allData.value = Outcome.success(updateDataUseCase.updateCommentText(commentUiModel),true)
    }

    fun updateCommentToggle(commentUiModel: CommentDataUiModel){
        allData.value = Outcome.success(updateDataUseCase.updateCommentToggle(commentUiModel))
    }

    fun updateImagePath(path: String){
        currentImageDataUiModel?.let {
            allData.value = Outcome.success(updateDataUseCase.updateImageData( it.copy(imagePath = path)))
        }
    }

    fun removeImage(imageDataUiModel: ImageDataUiModel){
        allData.value = Outcome.success(updateDataUseCase.removeImage(imageDataUiModel))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}