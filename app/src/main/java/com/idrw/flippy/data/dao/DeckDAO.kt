package com.idrw.flippy.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import kotlinx.coroutines.flow.Flow;

import com.idrw.flippy.data.model.Deck;

@Dao
interface DeckDAO {
    @Query(
        "SELECT " +
        "    decks.id, " +
        "    decks.title, " +
        "    decks.cards, " +
        "    decks.progress, " +
        "    decks.emoji, " +
        "    decks.emojiColor, " +
        "    COUNT(flashcards.learnStatus) AS cardsLearned " +
        "FROM decks " +
        "LEFT JOIN flashcards " +
        "ON flashcards.deckId = decks.id " +
        "AND flashcards.learnStatus = 'LEARNED' " +
        "GROUP BY decks.id"
    )
    fun getAllDecks(): Flow<List<Deck>>

    @Query("SELECT * FROM decks WHERE id = :id")
    fun findDeckById(id: Int): Flow<Deck>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(deck: Deck)

    @Update
    suspend fun update(deck: Deck)

    @Query("UPDATE decks SET cards = cards + 1 WHERE id = :deckId")
    suspend fun incrementCountById(deckId: Int)

    @Query("UPDATE decks SET cards = cards - 1 WHERE id = :deckId")
    suspend fun decrementCountById(deckId: Int)

    @Delete
    suspend fun delete(deck: Deck)
}
