package com.idrw.flippy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.idrw.flippy.data.model.Flashcard
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDAO {
    @Query("SELECT * from flashcards WHERE deckId = :deckId")
    fun getAllByDeckId(deckId: Int): Flow<List<Flashcard>>

    @Query("SELECT * FROM flashcards WHERE id = :id")
    fun findById(id: Int): Flow<Flashcard>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(flashcard: Flashcard)

    @Update
    suspend fun update(flashcard: Flashcard)

    @Delete
    suspend fun delete(item: Flashcard)
}