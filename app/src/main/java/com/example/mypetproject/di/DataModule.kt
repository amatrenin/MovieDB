package com.example.mypetproject.di

import com.example.mypetproject.ApiInterface
import com.example.mypetproject.model.repository.FirebaseRepository
import com.example.mypetproject.model.repository.FirebaseRepositoryImpl
import com.example.mypetproject.model.repository.MoviesDBRepository
import com.example.mypetproject.model.repository.MoviesDBRepositoryImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Module
@InstallIn(SingletonComponent::class)
@ExperimentalCoroutinesApi
class DataModule {

    @Provides
    fun provideApiInterfaceMovie() : ApiInterface = ApiInterface.create()

    @Provides
    fun provideMovieRepository(apiInterface: ApiInterface) : MoviesDBRepository {
       return MoviesDBRepositoryImpl(apiInterface = apiInterface)
    }

    @Provides
    fun provideDatabaseReferense() : DatabaseReference = Firebase.database.reference

    @Provides
    fun provideDatabaseRepository(mDatabase: DatabaseReference) : FirebaseRepository {
        return FirebaseRepositoryImpl(mDatabase = mDatabase)
    }

}