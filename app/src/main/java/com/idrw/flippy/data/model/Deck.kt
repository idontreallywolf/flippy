package com.idrw.flippy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val cards: Int,
    val progress: Float
)