package com.idrw.flippy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.idrw.flippy.data.dao.DeckDAO
import com.idrw.flippy.data.dao.FlashcardDAO
import com.idrw.flippy.data.model.Deck
import com.idrw.flippy.data.model.Flashcard
import kotlin.concurrent.Volatile

@Database(
    version = 3,
    entities = [Flashcard::class, Deck::class],
    exportSchema = false
)
abstract class FlippyAppDB: RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDAO
    abstract fun deckDao(): DeckDAO

    companion object {
        @Volatile
        private var Instance: FlippyAppDB? = null

        fun getDatabase(context: Context): FlippyAppDB {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlippyAppDB::class.java, "FlippyAppDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            .also { Instance = it }
        }
    }
}