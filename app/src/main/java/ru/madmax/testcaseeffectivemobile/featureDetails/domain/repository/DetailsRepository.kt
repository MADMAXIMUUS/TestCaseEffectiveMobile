package ru.madmax.testcaseeffectivemobile.featureDetails.domain.repository

import ru.madmax.testcaseeffectivemobile.featureDetails.domain.model.Details

interface DetailsRepository {

    suspend fun getDetails(): Details?
}