package ru.madmax.testcaseeffectivemobile.featureDetails.data.repository

import ru.madmax.testcaseeffectivemobile.featureDetails.data.dataSource.DetailsApi
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.model.Details
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.repository.DetailsRepository

class DetailsRepositoryImpl(private val detailsApi: DetailsApi) : DetailsRepository {

    override suspend fun getDetails(): Details? {
        return detailsApi.getDetails()
    }

}