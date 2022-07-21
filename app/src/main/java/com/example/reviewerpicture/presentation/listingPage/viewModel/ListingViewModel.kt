package com.example.reviewerpicture.presentation.listingPage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.domain.usecase.GetAllDataUseCase
import com.example.reviewerpicture.domain.usecase.SubmitDataUseCase
import com.example.reviewerpicture.domain.usecase.UpdateDataUseCase

class ListingViewModel(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val submitDataUseCase: SubmitDataUseCase
    ): ViewModel() {

    val allData: MutableLiveData<List<AllDataUiModel>> = MutableLiveData()

    fun getAllData(): List<AllDataNetworkModel> {
        val newData = getAllDataUseCase.execute()
//        allData.value = newData

        return newData
    }

    fun submitResponse(){
        submitDataUseCase.execute()
        //        allData.value = newData
    }

    fun updateData(dataUiModel: AllDataUiModel){
        updateDataUseCase.execute(dataUiModel)
        //        allData.value = newData
    }

}