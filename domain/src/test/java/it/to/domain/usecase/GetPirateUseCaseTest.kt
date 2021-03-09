package it.to.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import it.to.domain.entity.PirateShipEntity
import it.to.domain.repository.PirateShipsRepository
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.coInvoking
import org.amshove.kluent.shouldThrow
import org.junit.Test

/**
 * Test class for [GetPirateUseCase].
 */
class GetPirateUseCaseTest {

    private val pirateShipsRepository: PirateShipsRepository = mockk()
    private val target = GetPirateUseCase(pirateShipsRepository)

    @Test
    fun `given a list of pirate ships, when asking for the pirate ships list, then return the list`() {
        coEvery { pirateShipsRepository.fetchPirateShips() } returns STUB_LIST_PIRATES_SHIPS

        runBlocking {
            target.execute() `should be equal to` STUB_LIST_PIRATES_SHIPS
        }
    }

    @Test
    fun `given an empty list, when asking for the pirate ships list, then return an empty list`() {
        coEvery { pirateShipsRepository.fetchPirateShips() } returns emptyList()

        runBlocking {
            target.execute() `should be equal to` emptyList()
        }
    }

    @Test
    fun `given an exception, when asking for the pirate ships list, then throw the same exception`() {
        coEvery { pirateShipsRepository.fetchPirateShips() } throws STUB_EXCEPTION

        runBlocking {
            coInvoking { target.execute() } shouldThrow STUB_EXCEPTION
        }
    }

    private companion object {
        val STUB_PIRATES_SHIPS_ENTITY = PirateShipEntity(
            12, "How misty. You ransack like an ale.",
            "Anchors scream on yellow fever at haiti! How sunny. You haul like a cloud.",
            34, "http://images.bit-tech.net/content_images/2008/01/pirates_of_the_burning_sea/p.jpg",
            "ah"
        )
        val STUB_LIST_PIRATES_SHIPS = listOf(STUB_PIRATES_SHIPS_ENTITY, STUB_PIRATES_SHIPS_ENTITY.copy(id = 13))

        val STUB_EXCEPTION = Exception()
    }
}