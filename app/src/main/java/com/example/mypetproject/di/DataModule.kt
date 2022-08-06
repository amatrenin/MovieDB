//package com.example.mypetproject.di
//
//import com.example.mypetproject.ApiInterface
//import com.example.mypetproject.model.repository.MoviesDBRepository
//import com.example.mypetproject.model.repository.MoviesDBRepositoryImpl
//import dagger.Module
//
//
//@Module
//class DataModule {
//    fun provideMovieRepository(apiInterface: ApiInterface) : MoviesDBRepository {
//       return MoviesDBRepositoryImpl(apiInterface = apiInterface)
//    }
//}