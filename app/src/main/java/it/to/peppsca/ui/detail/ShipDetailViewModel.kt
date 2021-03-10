package it.to.peppsca.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.to.peppsca.ui.model.PirateShipModel
import javax.inject.Inject

/**
 * [ShipDetailFragment]'s view model.
 */
@HiltViewModel
class ShipDetailViewModel @Inject constructor() : ViewModel() {

    /**
     * The information about the list of pirate ships.
     */
    val pirateShip = MutableLiveData<PirateShipModel>()

    /**
     * Update detail fragment via databinding.
     */
    fun setUpShip(pirateShipModel: PirateShipModel) {
        pirateShip.postValue(pirateShipModel)
    }

}
