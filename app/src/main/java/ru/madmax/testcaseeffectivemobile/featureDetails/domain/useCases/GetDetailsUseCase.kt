package ru.madmax.testcaseeffectivemobile.featureDetails.domain.useCases

import ru.madmax.testcaseeffectivemobile.featureDetails.domain.model.Details
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.repository.DetailsRepository

class GetDetailsUseCase(val repository: DetailsRepository) {

    suspend operator fun invoke(): Details?{
        return repository.getDetails()
    }
}