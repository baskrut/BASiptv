package com.andrewbas.basiptv.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(
    @PrimaryKey(autoGenerate = true)

    val title: String,
    val videoSource: String,
    val logo: String,
    val type: String,
    val chNumber: String, //String because its gets from String and puts in TextView
    val fav: Boolean)