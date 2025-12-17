package org.mathieu.cleanrmapi.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.mathieu.cleanrmapi.data.local.CharacterLocal
import org.mathieu.cleanrmapi.data.local.objects.CharacterObject
import org.mathieu.cleanrmapi.data.local.objects.toModel
import org.mathieu.cleanrmapi.data.local.objects.toRealmObject
import org.mathieu.cleanrmapi.data.remote.CharacterApi
import org.mathieu.cleanrmapi.data.remote.responses.CharacterResponse
import org.mathieu.cleanrmapi.data.remote.responses.LocationResponse
import org.mathieu.cleanrmapi.domain.models.character.Character
import org.mathieu.cleanrmapi.domain.models.character.LocationPreview
import org.mathieu.cleanrmapi.domain.models.location.Location
import org.mathieu.cleanrmapi.domain.repositories.CharacterRepository
import org.mathieu.cleanrmapi.domain.repositories.LocationRepository

private const val LOCATION_PREFS = "location_repository_preferences"
private val nextPage = intPreferencesKey("next_locations_page_to_load")

private val Context.dataStore by preferencesDataStore(
    name = LOCATION_PREFS
)

internal class LocationRepositoryImpl(
    private val context: Context,
    private val locationApi: CharacterApi
) : LocationRepository {

    override suspend fun getLocation(id: Int): Location =
        locationApi.getLocationId(id = id)?.toDomain()
            ?: throw Exception("Location not found")
}
private fun LocationResponse.toDomain(): Location? {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}
