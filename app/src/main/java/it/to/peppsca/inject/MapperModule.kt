package it.to.peppsca.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.to.domain.entity.PirateShipEntity
import it.to.domain.mapper.Mapper
import it.to.peppsca.ui.mapper.PirateShipEntityToModelMapper
import it.to.peppsca.ui.model.PirateShipModel
import javax.inject.Singleton

/**
 * Declares presentation mappers
 */
@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    @Singleton
    fun guestEntityToGuestState(target: PirateShipEntityToModelMapper): Mapper<PirateShipEntity, PirateShipModel>

}
