package br.com.fiap.room_android.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.room_android.model.Vinho

@Database(entities = [Vinho::class], version = 1)
abstract class VinhoDb: RoomDatabase() {
    abstract fun vinhoDao(): VinhoDao
    companion object {
        private lateinit var instance: VinhoDb

        fun getDatabase(context: Context): VinhoDb {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        VinhoDb::class.java,
                        "vinho_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

}