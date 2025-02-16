package com.example.habittracker.di

import com.example.habittracker.data.repo.HabitRepositoryImpl
import com.example.habittracker.domain.repo.HabitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindHabitRepository(habitRepositoryImpl: HabitRepositoryImpl): HabitRepository
}
