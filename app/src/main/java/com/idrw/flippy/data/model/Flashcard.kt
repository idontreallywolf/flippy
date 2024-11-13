package com.idrw.flippy.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.idrw.flippy.ui.component.LearnStatus

@Entity(
    tableName = "flashcards",
    foreignKeys = [
        ForeignKey(
            entity = Deck::class,
            childColumns = ["deckId"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Flashcard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val deckId: Int,
    val frontText: String,
    val backText: String,
    val learnStatus: LearnStatus
)