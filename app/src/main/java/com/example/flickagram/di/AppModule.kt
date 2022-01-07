package com.example.flickagram.di


import com.example.flickagram.Model.ImageApi
import com.example.flickagram.repository.repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getApi(): ImageApi = Retrofit.Builder()
        .baseUrl("https://pixabay.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ImageApi::class.java)

    @Singleton
    @Provides
    fun getRepository(): repository = repository(getApi())

}
