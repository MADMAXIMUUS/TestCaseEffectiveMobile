package ru.madmax.testcaseeffectivemobile.featureExplorer.data.dataSource

import retrofit2.http.GET
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Explorer

interface ExplorerApi {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getItems(): Explorer?
}