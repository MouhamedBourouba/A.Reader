package com.example.areader.di


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.areader.data.api.AuthApi
import com.example.areader.data.api.BooksApi
import com.example.areader.data.api.UserApi
import com.example.areader.data.repository.auth.AuthRepository
import com.example.areader.data.repository.auth.AuthRepositoryImp
import com.example.areader.data.repository.details.DetailsRepository
import com.example.areader.data.repository.details.DetailsRepositoryImp
import com.example.areader.data.repository.home.HomeRepository
import com.example.areader.data.repository.home.HomeRepositoryImp
import com.example.areader.data.repository.search.SearchRepository
import com.example.areader.data.repository.search.SearchRepositoryImp
import com.example.areader.data.repository.updateBooks.UpdateRepository
import com.example.areader.data.repository.updateBooks.UpdateRepositoryImp
import com.example.areader.utils.Constants
import com.example.areader.utils.Constants.BASE_URL
import com.example.areader.utils.TextUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {
    @Provides
    @Singleton
    fun providesApi(): AuthApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("user", MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providesAuthRepository(authApi: AuthApi, sharedPreferences: SharedPreferences): AuthRepository {
        return AuthRepositoryImp(authApi, sharedPreferences)
    }

    @Singleton
    @Provides
    fun providesUpdateRepository(userApi: UserApi): UpdateRepository {
        return UpdateRepositoryImp(userApi)
    }


    @Singleton
    @Provides
    fun providesDetailsRepository(booksApi: BooksApi, userApi: UserApi): DetailsRepository {
        return DetailsRepositoryImp(booksApi, userApi)
    }

    @Singleton
    @Provides
    fun providesHomeRepository(userApi: UserApi): HomeRepository {
        return HomeRepositoryImp(userApi)
    }

    @Singleton
    @Provides
    fun providesUserApi(): UserApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserApi::class.java)

    @Singleton
    @Provides
    fun providesSearchRepository(api: BooksApi): SearchRepository {
        return SearchRepositoryImp(api)
    }

    @Singleton
    @Provides
    fun providesBooksApi(): BooksApi = Retrofit.Builder()
        .baseUrl(Constants.BOOKS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BooksApi::class.java)


    @Singleton
    @Provides
    fun providesTextUtils(): TextUtils {
        return TextUtils()
    }

}