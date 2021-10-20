package com.klt.gbs.di

import android.content.Context
import androidx.room.Room
import com.klt.gbs.BuildConfig
import com.klt.gbs.BuildConfig.BASE_URL
import com.klt.gbs.app.AppConstant
import com.klt.gbs.data.Repository
import com.klt.gbs.data.RepositoryImpl
import com.klt.gbs.data.local.DbDataSource
import com.klt.gbs.data.local.DbDataSourceImpl
import com.klt.gbs.data.local.MovieDatabase
import com.klt.gbs.data.remote.ApiDataSource
import com.klt.gbs.data.remote.ApiDataSourceImpl
import com.klt.gbs.data.remote.ApiService
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

// Hilt : 2 - module class for dependencies
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiDataSource(apiService: ApiService): ApiDataSource = ApiDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideDbDataSource(db: MovieDatabase): DbDataSource = DbDataSourceImpl(db)

    @Provides
    @Singleton
    fun provideRepoDataSource(api: ApiDataSource, db: DbDataSource): Repository =
        RepositoryImpl(api, db)

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient, moshi: Moshi): ApiService = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())

        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideMoshi() : Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

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

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            AppConstant.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

}