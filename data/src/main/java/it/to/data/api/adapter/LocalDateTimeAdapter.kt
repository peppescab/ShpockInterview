package it.to.data.api.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import it.to.data.api.adapter.LocalDateTimeAdapter.Companion.DEFAULT_DATE_FORMAT
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Json adapter that converts a string with a [DEFAULT_DATE_FORMAT] into a [LocalDateTime]
 */
class LocalDateTimeAdapter @Inject constructor() : TypeAdapter<LocalDateTime>() {
    override fun write(jsonReader: JsonWriter, value: LocalDateTime?) {
        if (value == null) {
            jsonReader.nullValue()
        } else {
            jsonReader.jsonValue(value.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
        }
    }

    override fun read(jsonReader: JsonReader): LocalDateTime? {
        return if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            null
        } else {
            LocalDateTime.parse(
                jsonReader.nextString(),
                DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)
            )
        }
    }

    companion object {
        /**
         * Api date format
         */
        const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    }
}
