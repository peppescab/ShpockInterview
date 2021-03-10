package it.to.peppsca.ui.mapper

import it.to.domain.mapper.Mapper
import javax.inject.Inject

/**
 * Mapper for greetings
 */
class GreetingMapper @Inject constructor() : Mapper<String?, String> {

    override fun map(input: String?): String =
        when (input) {
            "ah" -> "Ahoi!"
            "ay" -> "Aye Aye!"
            "ar" -> "Arrr!"
            "yo" -> "Yo ho hooo!"
            else -> "Ahoi!"
        }
}