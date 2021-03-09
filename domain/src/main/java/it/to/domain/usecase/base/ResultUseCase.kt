package it.to.domain.usecase.base

/**
 * Use case interface that outputs an [Output].
 */
interface ResultUseCase<Output> {

    /**
     * Executes the given use case and output [Output].
     *
     * @return [Output] as the result of the use case.
     */
    suspend fun execute(): Output
}
