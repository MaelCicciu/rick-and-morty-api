package org.mathieu.cleanrmapi.ui.screens.characterdetails

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.cleanrmapi.domain.repositories.CharacterRepository
import org.mathieu.cleanrmapi.ui.core.ViewModel


class CharacterDetailsViewModel(application: Application) : ViewModel<CharacterDetailsState>(CharacterDetailsState(), application) {

    private val characterRepository: CharacterRepository by inject()

    /**
     * The init function uses the repositories function to communicate with the data layer
     * It will use a characterId to fetch the character's information and a preview of their location
     * In this function, the first fetch is used to specifie the 2nd one
     *
     *
     * @param characterId The unique identifier of the character to be fetched.
     * @return Details of the specified character and their location.
     */
    fun init(characterId: Int) {
        fetchData(
            source = { characterRepository.getCharacter(id = characterId) }
        ) {

            onSuccess { character ->
                val locId = character.getLocationId()
                updateState { copy(avatarUrl = character.avatarUrl, name = character.name, error = null) }

                fetchData(
                    source = { characterRepository.getLocationPreview(id = locId) }
                ) {

                    onSuccess {
                        updateState { copy(locId = it.id, locName = it.name, locType = it.type, error = null) }
                    }

                    onFailure {
                        updateState { copy(error = it.toString()) }
                    }

                    updateState { copy(isLoading = false) }
                }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }
}


data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val error: String? = null,
    val locId: Int = -1,
    val locName: String = "",
    val locType: String = ""
)