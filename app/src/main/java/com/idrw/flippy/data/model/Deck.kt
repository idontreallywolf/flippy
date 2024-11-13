package com.idrw.flippy.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val cards: Int = 0,
    val cardsLearned: Int = 0,
    val progress: Float = 0.0f
)