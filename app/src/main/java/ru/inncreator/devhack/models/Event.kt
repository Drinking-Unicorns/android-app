package ru.inncreator.devhack.models

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Event(

    var id: Int?,
    var name: String?,
    var date: String?,
    var description: String?,
    var startTimeLong: Long?,
    var endTimeLong: Long?,
    var tags: ArrayList<Tag>?,
    var address: String?,
    var type: Int?,
    var winningPints: Int?,
    var eventPhotoUrl: String?
)

data class Tag(
    var tagId: Int?,
    var name: String?
)

fun Event.typeToString() = when (type) {
    0 -> "Тренировка"
    1 -> "Турнир"
    else -> "Мероприятие"
}

@SuppressLint("SimpleDateFormat")
fun Event.dateToString(): String {
    endTimeLong.also {
        if (it == null)
            return SimpleDateFormat("d H:m").format(Date(it))
    }
    startTimeLong?.let {
        val start = SimpleDateFormat("d").format(Date(it))
        val local = Locale("ru")
        val end = SimpleDateFormat("d MMMM", local).format(Date(endTimeLong!!))
        return "С $start по $end"
    }
    return "Пока не знаем((("
}

fun Event.winningPintsToString(): String {
    return "+ $winningPints баллов за участие"
}
