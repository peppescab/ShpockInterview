package it.to.data.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.to.data.repository.PirateShipsRepositoryImpl
import it.to.domain.repository.PirateShipsRepository
import javax.inject.Singleton

/**
 * Dagger module that handles repository dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun pirateShipsRepository(target: PirateShipsRepositoryImpl): PirateShipsRepository
}