package it.to.data.mapper

import it.to.data.dto.PirateShip
import it.to.domain.entity.PirateShipEntity
import it.to.domain.mapper.Mapper
import javax.inject.Inject

/**
 * Maps a [PirateShip] to a [PirateShipEntity].
 */
class PirateShipToEntityMapper @Inject constructor() : Mapper<PirateShip, PirateShipEntity> {

    override fun map(input: PirateShip): PirateShipEntity = with(input) {
        PirateShipEntity(
            id,
            title?:"Ship Default Title",
            description,
            price.toString(),
            image,
            greeting_type.orEmpty()
        )
    }
}