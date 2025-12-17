package org.mathieu.cleanrmapi.data.repositories

import kotlinx.serialization.InternalSerializationApi
import org.mathieu.cleanrmapi.data.remote.CharacterApi
import org.mathieu.cleanrmapi.data.remote.responses.LocationResponse
import org.mathieu.cleanrmapi.domain.models.location.Location
import org.mathieu.cleanrmapi.domain.repositories.LocationRepository

internal class LocationRepositoryImpl(
    private val locationApi: CharacterApi
) : LocationRepository {

    /**
     * Retrieves the details of the location with the specified ID.
     *
     * The function follows these steps:
     * 1. Fetches the location from the API using the id of the location
     * 2. Uses toDomain() to map the DTO model to the domain model
     * 3. If no data is fetched, throws an exception
     *
     * @param id The unique identifier of the location to retrieve.
     * @return The [Location] object representing the location details.
     * @throws Exception If the character cannot be found via the API.
     */
    @OptIn(InternalSerializationApi::class)
    override suspend fun getLocation(id: Int): Location =
        locationApi.getLocationId(id = id)?.toDomain()
            ?: throw Exception("Location not found")
}
@OptIn(InternalSerializationApi::class)
private fun LocationResponse.toDomain(): Location? {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}
