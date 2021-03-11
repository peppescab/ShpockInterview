package it.to.peppsca.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.to.peppsca.utils.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import it.to.domain.usecase.GetPirateUseCase
import it.to.peppsca.ui.mapper.PirateShipEntityToModelMapper
import it.to.peppsca.ui.model.PirateShipModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * [ShipsListFragment]'s view model.
 */
@HiltViewModel
class ShipsListViewModel @Inject constructor(
    private val useCase: GetPirateUseCase,
    private val mapper: PirateShipEntityToModelMapper
) : ViewModel() {

    /**
     * The information about the list of pirate ships.
     */
    val pirateShips = MutableLiveData<Lce<List<PirateShipModel>>>()

    /**
     * Action on the Collected button.
     */
    fun loadPirateShips() {
        viewModelScope.launch {
            pirateShips.postValue(Lce.loading())
            try {
                val listPirateShip = useCase.execute()
                pirateShips.postValue(Lce.success(mapper.mapList(listPirateShip)))
            } catch (err: Exception) {
                Timber.e(err)
                pirateShips.postValue(Lce.error(err))
            }
        }
    }

}
