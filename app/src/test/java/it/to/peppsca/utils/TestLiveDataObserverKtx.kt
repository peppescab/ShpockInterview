package it.to.peppsca.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import io.mockk.CapturingSlot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import java.util.function.Predicate

/**
 * Check that the last emission of the [Observer] is the request [emission]
 *
 * @param emission the last expected emission
 */
inline fun <reified T : Any> Observer<T>.assertLastEmission(emission: T) {
    val slot = CapturingSlot<T>()
    verify { onChanged(capture(slot)) }
    assertEquals(slot.captured, emission)
}

/**
 * Check that the last emission of the [Observer] match the given [predicate]
 *
 * @param predicate as the predicate we want to match
 */
@RequiresApi(Build.VERSION_CODES.N)
inline fun <reified T : Any> Observer<T>.assertLastEmission(predicate: Predicate<T>) {
    val slot = CapturingSlot<T>()
    verify { onChanged(capture(slot)) }
    assertTrue(predicate.test(slot.captured))
}

/**
 * Check that all the emission of the [Observer] are equals to the expected [emissionList]
 *
 * @param emissionList the ordered list of the expected emission
 */
inline fun <reified T : Any> Observer<T>.assertEmissionList(emissionList: List<T>) {
    val slot = mutableListOf<T>()
    verify(atLeast = emissionList.size) { onChanged(capture(slot)) }
    assertEquals(slot, emissionList)
}
