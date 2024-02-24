package com.example.composetweetsapp.repo.di

import com.example.composetweetsapp.dataSource.TweetsNetworkDataSource
import com.example.composetweetsapp.dataSource.TweetsNetworkDataSourceImpl
import com.example.composetweetsapp.repo.TweetsRepo
import com.example.composetweetsapp.repo.TweetsRepoImpl
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
    fun provideTweetsRepo(tweetsNetworkDataSource: TweetsNetworkDataSource): TweetsRepo {
        return TweetsRepoImpl(tweetsNetworkDataSource)
    }
}