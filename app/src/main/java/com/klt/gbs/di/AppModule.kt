package com.klt.gbs.di

import android.content.Context
import androidx.room.Room
import com.klt.gbs.BuildConfig
import com.klt.gbs.app.AppConstant
import com.klt.gbs.data.AppRepository
import com.klt.gbs.data.local.AppDbHelper
import com.klt.gbs.data.local.DbHelper
import com.klt.gbs.data.local.MovieDatabase
import com.klt.gbs.data.remote.ApiHelper
import com.klt.gbs.data.remote.ApiService
import com.klt.gbs.data.remote.AppApiHelper
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


// Hilt : 2 - module class for dependencies
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /*
    We used AppRepository in VM with @Inject const, so we need to @Provides for it
    */
    @Provides
    @Singleton
    fun provideRepository(apiHelper: ApiHelper, dbHelper: DbHelper) =
        AppRepository(apiHelper, dbHelper)

    /*
    provideRepository needs ApiHelper and DbHelper to create, so we need to provides for them
    */
    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: AppApiHelper): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideDbHelper(dbHelper: AppDbHelper): DbHelper = dbHelper

    /*
    provideApiHelper needs AppApiHelper, provideDbHelper needs AppDbHelper
    */
    @Provides
    @Singleton
    fun provideAppApiHelper(apiService: ApiService) = apiService

    @Provides
    @Singleton
    fun provideAppDbHelper(database: MovieDatabase) = database

    /*
    provideAppApiHelper needs ApiService, provideAppDbHelper needs MovieDatabase
    */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context,
        dbName: String
    ): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, dbName).build()

    /*  provideApiService needs Retrofit to create  */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String,
        moShi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moShi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    /*  provideRetrofit needs 1. OkHttpClient, 2. BASE_URL, 3. Moshi    */

    //for provideRetrofit -- 1nd params okHttpClient for provideRetrofit
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {

        //this is for logging profiler
        OkHttpClient.Builder()
            .addInterceptor(OkHttpProfilerInterceptor())
            .addNetworkInterceptor(OkHttpProfilerInterceptor())
            .build()

        //.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    } else OkHttpClient
        .Builder()
        .build()

    //for provideRetrofit -- 2nd params BASE_URL for provideRetrofit
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    //for provideRetrofit -- 3rd params Moshi
    @Provides
    @Singleton
    fun providesMoShi(): Moshi = Moshi.Builder().build()

    /*  provideMovieDatabase needs 1. context( ady declared by @ApplicationContext), 2. databaseName    */

    //for provide MovieDatabase -- 2nd param dbName
    @Provides
    @Singleton
    fun provideDbName() = AppConstant.DB_NAME


}