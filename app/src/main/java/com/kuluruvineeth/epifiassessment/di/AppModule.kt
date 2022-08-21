package com.kuluruvineeth.epifiassessment.di

import com.kuluruvineeth.epifiassessment.utils.PanValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesPanValidator(): PanValidator {
        return PanValidator()
    }
}