package ru.madmax.testcaseeffectivemobile.featureExplorer.domain.useCases

import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Explorer
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.repository.ExplorerRepository

class GetItemsUseCase(val repository: ExplorerRepository) {

    suspend operator fun invoke(): Explorer? {
        return repository.getItems()
    }
}