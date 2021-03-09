package it.to.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import it.to.data.api.PiratesApi
import it.to.data.dto.PirateShip
import it.to.data.dto.PirateShipsResponse
import it.to.data.mapper.PirateShipToEntityMapper
import it.to.domain.entity.PirateShipEntity
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.coInvoking
import org.amshove.kluent.shouldThrow
import org.junit.Test
import java.lang.Exception

/**
 * Test class for [PirateShipsRepositoryImpl]
 */
class PirateShipsRepositoryImplTest {

    private val api: PiratesApi = mockk()
    private val mapper = PirateShipToEntityMapper()
    private val target = PirateShipsRepositoryImpl(api, mapper)

    @Test
    fun `given no errors, when fetching pirates ships, then return pirate ships entities`() {
        coEvery { api.pirateShipsGet() } returns STUB_PIRATES_SHIPS_RESPONSE
        runBlocking {
            target.fetchPirateShips() `should be equal to` listOf(STUB_PIRATES_SHIPS_ENTITY)
        }
    }

    @Test
    fun `given general error, when fetching pirates ships, then return error`() {
        coEvery { api.pirateShipsGet() } throws  STUB_EXCEPTION
        runBlocking {
            coInvoking { target.fetchPirateShips() } shouldThrow STUB_EXCEPTION
        }
    }

    private companion object {

        val STUB_PIRATES_SHIPS = PirateShip(
            12, "How misty. You ransack like an ale.",
            "Anchors scream on yellow fever at haiti! How sunny. You haul like a cloud.",
            34, "http://images.bit-tech.net/content_images/2008/01/pirates_of_the_burning_sea/p.jpg",
            "ah"
        )

        val STUB_PIRATES_SHIPS_ENTITY = PirateShipEntity(
            12, "How misty. You ransack like an ale.",
            "Anchors scream on yellow fever at haiti! How sunny. You haul like a cloud.",
            34, "http://images.bit-tech.net/content_images/2008/01/pirates_of_the_burning_sea/p.jpg",
            "ah"
        )
        val STUB_PIRATES_SHIPS_LIST = listOf(STUB_PIRATES_SHIPS, null)
        val STUB_PIRATES_SHIPS_RESPONSE = PirateShipsResponse(
            success = true,
            ships = STUB_PIRATES_SHIPS_LIST
        )

        val STUB_EXCEPTION = Exception()
    }
}