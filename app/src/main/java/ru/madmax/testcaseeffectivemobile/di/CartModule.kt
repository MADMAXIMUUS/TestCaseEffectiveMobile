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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Provides
    @Singleton
    fun provideCartApi(client: OkHttpClient): CartApi {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CartApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartApi: CartApi): CartRepository {
        return CartRepositoryImpl(cartApi)
    }

    @Provides
    @Singleton
    fun provideGetCartUseCase(repository: CartRepository): GetCartUseCase {
        return GetCartUseCase(repository)
    }

}