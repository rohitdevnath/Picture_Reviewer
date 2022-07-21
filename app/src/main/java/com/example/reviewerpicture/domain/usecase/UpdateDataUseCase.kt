package com.example.reviewerpicture.domain.usecase

import com.example.reviewerpicture.data.model.uiModel.AllDataUiModel
import com.example.reviewerpicture.domain.repository.Repository

class UpdateDataUseCase(private val repository: Repository) {

    fun execute(model: AllDataUiModel):List<AllDataUiModel> = repository.updateData(model)

}