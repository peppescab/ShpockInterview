package it.to.peppsca.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Pirate Ship model for presentation layer
 *
 * @property id is the id of this ship
 * @property title is relative to title for this ship
 * @property description is the description of this ship
 * @property image contains url of this ship
 * @property greeting is the greeting to be showed
 */
@Parcelize
data class PirateShipModel(
    val id: Long,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val greeting: String
) : Parcelable
