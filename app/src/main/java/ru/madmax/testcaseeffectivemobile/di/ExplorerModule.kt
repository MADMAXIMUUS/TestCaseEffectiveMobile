package ru.madmax.testcaseeffectivemobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.madmax.testcaseeffectivemobile.featureCart.data.dataSource.CartApi
import ru.madmax.testcaseeffectivemobile.featureCart.data.repository.CartRepositoryImpl
import ru.madmax.testcaseeffectivemobile.featureCart.domain.repository.CartRepository
import ru.madmax.testcaseeffectivemobile.featureCart.domain.useCases.GetCartUseCase
import ru.madmax.testcaseeffectivemobile.featureExplorer.data.dataSource.ExplorerApi
import ru.madmax.testcaseeffectivemobile.featureExplorer.data.repository.ExplorerRepositoryImpl
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.repository.ExplorerRepository
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.useCases.GetItemsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExplorerModule {

    @Provides
    @Singleton
    fun provideCartApi(client: OkHttpClient): ExplorerApi {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ExplorerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExplorerRepository(explorerApi: ExplorerApi): ExplorerRepository {
        return ExplorerRepositoryImpl(explorerApi)
    }

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: ExplorerRepository): GetItemsUseCase {
        return GetItemsUseCase(repository)
    }
}