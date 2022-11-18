package ru.madmax.testcaseeffectivemobile.featureCart.data.dataSource

import retrofit2.http.GET
import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Cart

interface CartApi {

    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): Cart?
}