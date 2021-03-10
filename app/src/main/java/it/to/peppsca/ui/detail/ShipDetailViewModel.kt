package it.to.peppsca.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.to.domain.entity.PirateShipEntity
import javax.inject.Inject

/**
 * [ShipDetailFragment]'s view model.
 */
@HiltViewModel
class ShipDetailViewModel @Inject constructor(

) : ViewModel() {

    /**
     * The information about the list of pirate ships.
     */
   val pirateShip = MutableLiveData<PirateShipEntity>()

    /**
     * Action on the Collected button.
     */
    fun setUpShip(pirateShip: PirateShipEntity) {
       pirateShip = pirateShip
    }

}
