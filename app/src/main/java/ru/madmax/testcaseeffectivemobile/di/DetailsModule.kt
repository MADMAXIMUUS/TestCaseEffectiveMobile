package ru.madmax.testcaseeffectivemobile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.madmax.testcaseeffectivemobile.featureCart.domain.repository.CartRepository
import ru.madmax.testcaseeffectivemobile.featureCart.domain.useCases.GetCartUseCase
import ru.madmax.testcaseeffectivemobile.featureDetails.data.dataSource.DetailsApi
import ru.madmax.testcaseeffectivemobile.featureDetails.data.repository.DetailsRepositoryImpl
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.repository.DetailsRepository
import ru.madmax.testcaseeffectivemobile.featureDetails.domain.useCases.GetDetailsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    @Singleton
    fun provideDetailsApi(client: OkHttpClient): DetailsApi {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailsRepository(detailsApi: DetailsApi): DetailsRepository {
        return DetailsRepositoryImpl(detailsApi)
    }

    @Provides
    @Singleton
    fun provideGetDetailsUseCase(repository: DetailsRepository): GetDetailsUseCase {
        return GetDetailsUseCase(repository)
    }

}