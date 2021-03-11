package it.to.peppsca.utils

/**
 * Class for handling the state of a resource
 * Loading
 * Content
 * Error
 * If empty constructor is called it means that the resource is in Loading State
 *
 * @property loading is referred to Loading state
 * @property content is referred to Content
 * @property error is referred to error
 */
data class Lce<T>(
    val loading: Boolean? = true,
    val content: T? = null,
    val error: Throwable? = null,
) {
    /**
     * Return true if Lce does not contain error but contains data
     */
    val isSuccessful: Boolean = error == null && content != null

    /**
     * Return true if contains error
     */
    val hasError: Boolean = error != null

    companion object {

        /**
         * Short hand for creating a Lce that represents
         * a successful result.
         *
         * @param content as the optional content.
         */
        fun <T> success(content: T): Lce<T> = Lce(loading = false, content = content)

        /**
         * Short hand for creating a Lce that represents
         * a failed result.
         *
         * @param throwable as the error
         */
        fun <T> error(throwable: Throwable? = null): Lce<T> = Lce(loading = false, error = throwable)

        /**
         * Short hand for creating a Lce that represents
         * a loading status
         *
         * @param content as the optional content
         */
        fun <T> loading(content: T? = null): Lce<T> = Lce(loading = true, content = content)
    }
}
