package ru.madmax.testcaseeffectivemobile.featureDetails.data.dataSource

import retrofit2.http.GET
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.model.Details

interface DetailsApi {

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails(): Details?
}