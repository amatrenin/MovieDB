package com.example.mypetproject.di

import com.example.mypetproject.ApiInterface
import com.example.mypetproject.model.repository.MoviesDBRepository
import com.example.mypetproject.model.repository.MoviesDBRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideApiInterfaceMovie() : ApiInterface = ApiInterface.create()

    @Provides
    fun provideMovieRepository(apiInterface: ApiInterface) : MoviesDBRepository {
       return MoviesDBRepositoryImpl(apiInterface = apiInterface)
    }
}