package com.example.reviewerpicture.domain.usecase

import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.domain.repository.Repository
import io.reactivex.Single

class GetAllDataUseCase(private val repository: Repository) {

    fun execute(forceRemote: Boolean): Single<List<AllDataUiModel>> = repository.getData(forceRemote)

}