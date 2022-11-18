package ru.madmax.testcaseeffectivemobile.featureExplorer.domain.repository

import ru.madmax.testcaseeffectivemobile.featureExplorer.data.dataSource.ExplorerApi
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Explorer

interface ExplorerRepository {

    suspend fun getItems(): Explorer?
}