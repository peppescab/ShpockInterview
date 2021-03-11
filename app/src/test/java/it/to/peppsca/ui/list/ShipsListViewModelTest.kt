package it.to.peppsca.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import it.to.peppsca.utils.Lce
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import it.to.domain.entity.PirateShipEntity
import it.to.domain.usecase.GetPirateUseCase
import it.to.peppsca.rules.MainCoroutineRule
import it.to.peppsca.ui.mapper.GreetingMapper
import it.to.peppsca.ui.mapper.PirateShipEntityToModelMapper
import it.to.peppsca.ui.model.PirateShipModel
import it.to.peppsca.utils.assertEmissionList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * test for [ShipsListViewModel]
 */
class ShipsListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getPirateUseCase: GetPirateUseCase = mockk()
    private val mapper = PirateShipEntityToModelMapper(GreetingMapper())
    private val target = ShipsListViewModel(getPirateUseCase, mapper)

    @SpyK
    private var shipsObserver = Observer<Lce<List<PirateShipModel>>> {}

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        target.pirateShips.observeForever(shipsObserver)
    }

    @After
    fun tearDown() {
        target.pirateShips.removeObserver(shipsObserver)
    }

    @Test
    fun `given a valid usecase, when the viewModel , then load pirate ships`() {
        coEvery { getPirateUseCase.execute() } returns STUB_PIRATES_SHIPS_LIST_ENTITY

        target.loadPirateShips()

        shipsObserver.assertEmissionList(listOf(Lce.loading(), Lce.success(STUB_PIRATES_SHIPS_LIST_MODEL)))
    }

    @Test
    fun `given an exception, when the viewModel gets initialized, then visitorModel gets in loading and error states`() {
        coEvery { getPirateUseCase.execute() } throws STUB_EXCEPTION
        target.loadPirateShips()
        shipsObserver.assertEmissionList(listOf(Lce.loading(), Lce.error(STUB_EXCEPTION)))
    }

    private companion object {

        val STUB_PIRATES_SHIPS = PirateShipModel(
            12, "How misty. You ransack like an ale.",
            "Anchors scream on yellow fever at haiti! How sunny. You haul like a cloud.",
            "34", "http://images.bit-tech.net/content_images/2008/01/pirates_of_the_burning_sea/p.jpg",
            "Ahoi!"
        )

        val STUB_PIRATES_SHIPS_ENTITY = PirateShipEntity(
            12, "How misty. You ransack like an ale.",
            "Anchors scream on yellow fever at haiti! How sunny. You haul like a cloud.",
            "34", "http://images.bit-tech.net/content_images/2008/01/pirates_of_the_burning_sea/p.jpg",
            "ah"
        )
        val STUB_PIRATES_SHIPS_LIST_ENTITY = listOf(STUB_PIRATES_SHIPS_ENTITY)
        val STUB_PIRATES_SHIPS_LIST_MODEL = listOf(STUB_PIRATES_SHIPS)
        val STUB_EXCEPTION = Exception()
    }
}
