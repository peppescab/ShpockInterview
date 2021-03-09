package it.to.data.repository

import it.to.data.api.PiratesApi
import it.to.data.mapper.PirateShipToEntityMapper
import it.to.domain.entity.PirateShipEntity
import it.to.domain.repository.PirateShipsRepository
import javax.inject.Inject

/**
 * Repository implementation of [PirateShipsRepository]
 */
class PirateShipsRepositoryImpl @Inject constructor(
    private val piratesApi: PiratesApi,
    private val mapper: PirateShipToEntityMapper
) : PirateShipsRepository {

    override suspend fun fetchPirateShips(): List<PirateShipEntity> =
        mapper.mapNotNull(
            piratesApi.pirateShipsGet().ships
        )
}
