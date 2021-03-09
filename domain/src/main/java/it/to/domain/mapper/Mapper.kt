package it.to.domain.mapper

import java.util.*

/**
 * Interface that represents a base mapper to convert [Input] type to [Output] type.
 */
interface Mapper<Input, Output> {
    /**
     * Transforms an input into output.
     *
     * @param input the input to be transformed
     * @return transformation result
     */
    fun map(input: Input): Output

    /**
     * Transforms a [List] of [Input] into a [List] of [Output].
     *
     * @param input the input to be transformed
     * @return transformation result
     */
    fun mapNotNull(input: List<Input?>): List<Output> {
        val result: MutableList<Output> = LinkedList()
        for (item in input) {
            if (item != null)
                result.add(map(item))
        }
        return result
    }
}
