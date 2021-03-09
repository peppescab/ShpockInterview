package it.to.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 *
 */
@Keep
data class PirateShip(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("greeting_type")
    val greeting_type: String?
)
