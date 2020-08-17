package com.andrewbas.basiptv

data class TvChanel(
    val title: String,
    val videoSource: String,
    val logo: String,
    val type: String,
    val chNumber: String )//todo зозможно нужно будет удалить в пользу package com.andrewbas.basiptv.db.Chanel