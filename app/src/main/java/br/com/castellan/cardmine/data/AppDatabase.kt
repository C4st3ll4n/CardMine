package br.com.castellan.cardmine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BussinessCard::class], version = 1)
abstract  class AppDatabase: RoomDatabase() {

    abstract fun bussinessDAO():BussinessCardDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context:Context):AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bussiness_card"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}