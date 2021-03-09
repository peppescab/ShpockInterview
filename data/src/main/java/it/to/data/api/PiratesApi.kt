package it.to.data.api

import it.to.data.dto.PirateShipsResponse
import retrofit2.http.GET

/**
 * Apis for interacting with visitor objects
 */
interface PiratesApi {

    /**
     * Gets a given visitor by its id
     */
    @GET("pirateships")
    suspend fun pirateShipsGet(): PirateShipsResponse

}
