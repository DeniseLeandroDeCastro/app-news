package br.com.trilhamobile.appnews.model.db

import androidx.room.TypeConverter
import br.com.trilhamobile.appnews.model.Source
import java.util.*

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}