package com.example.composetweetsapp.dataSource.di

import com.example.composetweetsapp.dataSource.TweetsNetworkDataSource
import com.example.composetweetsapp.dataSource.TweetsNetworkDataSourceImpl
import com.example.composetweetsapp.service.TweetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideTweetsNetworkDataSource(tweetsApi: TweetsApi): TweetsNetworkDataSource {
        return TweetsNetworkDataSourceImpl(tweetsApi)
    }
}