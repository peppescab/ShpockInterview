package it.to.peppsca.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.planetsmartcity.gatekeeper.utils.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import it.to.domain.entity.PirateShipEntity
import it.to.domain.usecase.GetPirateUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * [ShipsListFragment]'s view model.
 */
@HiltViewModel
class ShipsListViewModel @Inject constructor(
    private val useCase: GetPirateUseCase
) : ViewModel() {

    /**
     * The information about the list of pirate ships.
     */
    val pirateShips = MutableLiveData<Lce<List<PirateShipEntity>>>()

    /**
     * Action on the Collected button.
     */
    fun loadPirateShips() {
        viewModelScope.launch {
            try {
                val listPirateShip = useCase.execute()
                pirateShips.postValue(Lce.success(listPirateShip))
            } catch (err: Exception) {
                Timber.e(err)
                pirateShips.postValue(Lce.error(err))
            }
        }
    }

}
