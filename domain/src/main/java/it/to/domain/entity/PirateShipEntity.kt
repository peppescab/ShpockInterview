package it.to.domain.entity

/**
 * PirateShips model in domain layer
 */
data class PirateShipEntity(
    val id: Long,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val greeting_type: String
)
