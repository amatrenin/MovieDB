package com.example.mypetproject.di

import android.content.Context
import androidx.room.Room
import com.example.mypetproject.model.apis.ApiInterface
import com.example.mypetproject.model.repository.FirebaseRepository
import com.example.mypetproject.model.repository.FirebaseRepositoryImpl
import com.example.mypetproject.model.repository.MoviesDBRepository
import com.example.mypetproject.model.repository.MoviesDBRepositoryImpl
import com.example.mypetproject.room.AppDatabase
import com.example.mypetproject.room.RoomRepository
import com.example.mypetproject.room.RoomRepositoryImpl
import com.example.mypetproject.room.RoomRepositoryImpl.Companion.DATABASE_NAME
import com.example.mypetproject.room.ToDoDao
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
@ExperimentalCoroutinesApi

class DataModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DATABASE_NAME
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesAppDataBase(appDataBase: AppDatabase) = appDataBase.toDoDao()

    @Singleton
    @Provides
    fun providesRoomRepository(toDoDao: ToDoDao): RoomRepository = RoomRepositoryImpl(toDoDao)

    @Provides
    fun provideApiInterfaceMovie() : ApiInterface = ApiInterface.create()

    @Provides
    fun provideMovieRepository(mApiInterface: ApiInterface) : MoviesDBRepository {
       return MoviesDBRepositoryImpl(mApiInterface = mApiInterface)
    }

    @Provides
    fun provideDatabaseReferense() : DatabaseReference = Firebase.database.reference

    @Provides
    fun provideDatabaseRepository(mDatabase: DatabaseReference) : FirebaseRepository {
        return FirebaseRepositoryImpl(mDatabase = mDatabase)
    }

}