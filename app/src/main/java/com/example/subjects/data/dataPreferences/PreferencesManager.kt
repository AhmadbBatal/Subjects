package com.example.subjects.data.dataPreferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.subjects.util.enums.SubjectStatue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "preferenceManager"

data class FilterPreference(val sortStoreOrder: SubjectStatue)

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext val context: Context) {

    private val Context.dataStoreG: DataStore<Preferences> by preferencesDataStore("Google")

    val preferencesFlow = context.dataStoreG.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error Reading Preference", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val sortOrder = SubjectStatue.valueOf(
                preference[PreferencesKeys.SUBJECT_ORDER] ?: SubjectStatue.ALL.name
            )

            FilterPreference(sortOrder)
        }

    suspend fun updateSortOrder(sortOrder: SubjectStatue) {
        context.dataStoreG.edit { preferences ->
            preferences[PreferencesKeys.SUBJECT_ORDER] = sortOrder.name
        }
    }

    suspend fun getDataStore(sortOrder: SubjectStatue) {
        context.dataStoreG.edit { preference ->
            preference[PreferencesKeys.SUBJECT_ORDER] = sortOrder.name
        }
    }

    // Key
    private object PreferencesKeys {
        val SUBJECT_ORDER = stringPreferencesKey("sort_order")
    }
}
