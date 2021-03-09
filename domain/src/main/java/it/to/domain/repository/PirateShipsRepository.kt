package it.to.domain.repository

import it.to.domain.entity.PirateShipEntity


/**
 * Repository that handles pirate ships actions.
 */
interface PirateShipsRepository {

    /**
     * Fetch the pirate ships.
     */
    suspend fun fetchPirateShips(): List<PirateShipEntity>
}
