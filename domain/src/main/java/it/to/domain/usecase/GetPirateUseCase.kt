package it.to.domain.usecase

import it.to.domain.entity.PirateShipEntity
import it.to.domain.repository.PirateShipsRepository
import it.to.domain.usecase.base.ResultUseCase
import javax.inject.Inject

/**
 * This use case retrieves all pirates
 */
class GetPirateUseCase @Inject constructor(
    private val pirateShipsRepository: PirateShipsRepository
) : ResultUseCase<List<PirateShipEntity>> {
    override suspend fun execute(): List<PirateShipEntity> = pirateShipsRepository.fetchPirateShips()
}
