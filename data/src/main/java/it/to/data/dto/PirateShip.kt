package it.to.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Dto for a PirateShip
 *
 * @property id as id of the ship
 * @property title as the title of the ship
 * @property description as the description of the ship
 * @property price as the price of the ship
 * @property image as the url where to reach the image
 * @property greeting_type as the type of greeting
 */
@Keep
data class PirateShip(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("greeting_type")
    val greeting_type: String?
)
