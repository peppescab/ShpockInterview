package it.to.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 *
 */
@Keep
data class PirateShipsResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("ships")
    val ships: List<PirateShip?>
)