package com.example.idkkkk.di

import android.app.Application
import androidx.room.Room
import com.example.idkkkk.model.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ContactDatabase{
        val database = Room.databaseBuilder(
            app,
            ContactDatabase::class.java,
            "contacts_db"
        ).build()
        return database
    }
}