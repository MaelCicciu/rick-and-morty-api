package org.mathieu.cleanrmapi.data.repositories

import org.mathieu.cleanrmapi.data.remote.CharacterApi
import org.mathieu.cleanrmapi.data.remote.responses.LocationResponse
import org.mathieu.cleanrmapi.domain.models.location.Location
import org.mathieu.cleanrmapi.domain.repositories.LocationRepository

internal class LocationRepositoryImpl(
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
