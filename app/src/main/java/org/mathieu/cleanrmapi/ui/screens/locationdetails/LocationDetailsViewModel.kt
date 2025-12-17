package org.mathieu.cleanrmapi.ui.screens.locationdetails

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.cleanrmapi.domain.repositories.LocationRepository
import org.mathieu.cleanrmapi.ui.core.ViewModel


class LocationDetailsViewModel(application: Application) : ViewModel<LocationDetailsState>(LocationDetailsState(), application) {

    private val locationRepository: LocationRepository by inject()

    fun init(locationId: Int) {
        fetchData(
            source = { locationRepository.getLocation(id = locationId) }
        ) {

            onSuccess {
                updateState { copy(name = it.name, type = it.type, dimension = it.dimension, error = null) }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }
}


data class LocationDetailsState(
    val isLoading: Boolean = true,
    val name: String = "",
    val type: String = "",
    val dimension: String = "",
    val error: String? = null,
)