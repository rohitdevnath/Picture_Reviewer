package com.example.reviewerpicture.domain.usecase

import com.example.reviewerpicture.domain.repository.Repository

class SubmitDataUseCase(private val repository: Repository) {

    fun execute() = repository.submitData(listOf())
}