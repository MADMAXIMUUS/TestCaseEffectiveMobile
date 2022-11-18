package ru.madmax.testcaseeffectivemobile.featureExplorer.data.repository

import ru.madmax.testcaseeffectivemobile.featureExplorer.data.dataSource.ExplorerApi
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Explorer
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.repository.ExplorerRepository

class ExplorerRepositoryImpl(private val explorerApi: ExplorerApi) : ExplorerRepository {

    override suspend fun getItems(): Explorer? {
        return explorerApi.getItems()
    }
}