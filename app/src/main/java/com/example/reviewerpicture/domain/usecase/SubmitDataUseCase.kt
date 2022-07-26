package com.example.reviewerpicture.domain.usecase

import com.example.reviewerpicture.domain.repository.Repository
import io.reactivex.Single

class SubmitDataUseCase(private val repository: Repository) {

    fun execute(): Single<Unit> = repository.submitData()
}