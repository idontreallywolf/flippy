package com.idrw.flippy.ui.view.deck

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class DeckViewModel: ViewModel() {
    var flashcards: MutableList<Flashcard> = mutableStateListOf()
        private set

    fun filterFlashcards(learnStatus: LearnStatus): List<Flashcard> {
        return flashcards.filter { it.learnStatus == learnStatus }
    }

    fun generateRandomFlashcards(count: Int = 10) {
        if (flashcards.size > 0) {
            return
        }

        val frontTextSamples = listOf("What is the capital of France?", "Define polymorphism.", "What is 2 + 2?", "Explain inheritance in OOP.", "Name the planets in our solar system.")
        val backTextSamples = listOf("Paris", "Ability of a function to handle objects of many types", "4", "A mechanism to create a new class using an existing class", "Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune")
        val learnStatusValues = LearnStatus.entries.toTypedArray()

        val fc = List(count) {
            Flashcard(
                id = "123",
                deckId = "456",
                frontText = frontTextSamples.random(),
                backText = backTextSamples.random(),
                learnStatus = learnStatusValues.random()
            )
        }

        flashcards.addAll(fc)
    }
}