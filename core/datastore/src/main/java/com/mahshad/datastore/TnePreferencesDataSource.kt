package com.mahshad.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class TnePreferencesDataSource @Inject constructor(
    private val userPreferences:
    DataStore<Preferences>
) {
}