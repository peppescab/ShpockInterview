package it.to.peppsca.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.SpyK
import it.to.peppsca.rules.MainCoroutineRule
import it.to.peppsca.ui.model.PirateShipModel
import it.to.peppsca.utils.assertEmissionList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * test for [ShipDetailViewModel]
 */
class ShipDetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val target = ShipDetailViewModel()

    @SpyK
    private var shipsObserver = Observer<PirateShipModel> {}

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        target.pirateShip.observeForever(shipsObserver)
    }

    @After
    fun tearDown() {
        target.pirateShip.removeObserver(shipsObserver)
    }

    @Test
    fun `given a valid usecase, when the viewModel , then load pirate ships`() {
        target.setUpShip(STUB_PIRATES_SHIP_MODEL)
        shipsObserver.assertEmissionList(listOf(STUB_PIRATES_SHIP_MODEL))
    }

    private companion object {

        val STUB_PIRATES_SHIP_MODEL = PirateShipModel(
            12, "How misty. You ransack like an ale.",
            "Anchors scream on yellow fever at haiti! How sunny. You haul like a cloud.",
            "34", "http://images.bit-tech.net/content_images/2008/01/pirates_of_the_burning_sea/p.jpg",
            "Ahoi!"
        )

    }
}