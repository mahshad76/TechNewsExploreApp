package com.mahshad.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mahshad.datastore.TnePreferencesDataSource
import com.mahshad.datastore.TnePreferencesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule() {
    @Binds
    abstract fun bindTnePreferenceDataSource(tnePreferenceDataStore: TnePreferencesDataSourceImpl):
            TnePreferencesDataSource

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "user_prefs")

        @Provides
        @Singleton
        fun providePreferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
            context.dataStore
    }
}

