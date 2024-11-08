package com.idrw.flippy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.idrw.flippy.ui.view.deck.LearnStatus

@Entity(tableName = "flashcards")
data class Flashcard(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val deckId: String,
    val frontText: String,
    val backText: String,
    val learnStatus: LearnStatus
)