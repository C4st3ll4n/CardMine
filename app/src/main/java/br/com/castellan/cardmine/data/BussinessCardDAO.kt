package br.com.castellan.cardmine.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BussinessCardDAO {

    @Query("SELECT * FROM BussinessCard")
    fun getAll(): LiveData<List<BussinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bussinessCard: BussinessCard)
}