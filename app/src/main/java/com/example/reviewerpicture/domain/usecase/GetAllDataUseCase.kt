package com.example.reviewerpicture.domain.usecase

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.domain.repository.Repository

class GetAllDataUseCase(private val repository: Repository) {

    fun execute():List<AllDataNetworkModel> = repository.getData()

}