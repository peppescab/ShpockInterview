package it.to.peppsca.ui.mapper

import it.to.domain.entity.PirateShipEntity
import it.to.domain.mapper.Mapper
import it.to.peppsca.ui.model.PirateShipModel
import javax.inject.Inject

/**
 * Maps a [PirateShipEntity] to a [PirateShipModel].
 */
class PirateShipEntityToModelMapper @Inject constructor(
    private val mapper: GreetingMapper
) : Mapper<PirateShipEntity, PirateShipModel> {

    override fun map(input: PirateShipEntity): PirateShipModel = with(input) {
        PirateShipModel(
            id,
            title,
            description,
            price,
            image,
            mapper.map(greeting_type)
        )
    }
}