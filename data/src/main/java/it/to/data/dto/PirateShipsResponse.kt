package it.to.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Dto that keeps info about Response
 *
 * @property success if true the call has success
 * @property ships contains the list of shipss
 */
@Keep
data class PirateShipsResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("ships")
    val ships: List<PirateShip?>
)