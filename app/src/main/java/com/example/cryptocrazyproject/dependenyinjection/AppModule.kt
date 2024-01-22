package com.example.cryptocrazyproject.dependenyinjection

import com.example.cryptocrazyproject.repository.CryptoRepository
import com.example.cryptocrazyproject.repository.CryptoRepositoryInterface
import com.example.cryptocrazyproject.service.CryptoAPI
import com.example.cryptocrazyproject.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Singleton
    @Provides
    fun provideCryptoAPI() : CryptoAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api : CryptoAPI)  :CryptoRepositoryInterface {
        return CryptoRepository(api)
    }


}