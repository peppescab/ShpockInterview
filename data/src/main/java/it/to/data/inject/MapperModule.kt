package it.to.data.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.to.data.dto.PirateShip
import it.to.data.mapper.PirateShipToEntityMapper
import it.to.domain.entity.PirateShipEntity
import it.to.domain.mapper.Mapper
import javax.inject.Singleton

/**
 * Dagger module for Mappers dependency.
 */
@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    @Singleton
    fun providePirateShipMapper(target: PirateShipToEntityMapper): Mapper<PirateShip, PirateShipEntity>
}
