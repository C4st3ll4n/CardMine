package br.com.castellan.cardmine

import android.app.Application
import br.com.castellan.cardmine.data.AppDatabase
import br.com.castellan.cardmine.data.BussinessCardRepository

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BussinessCardRepository(database.bussinessDAO()) }
}