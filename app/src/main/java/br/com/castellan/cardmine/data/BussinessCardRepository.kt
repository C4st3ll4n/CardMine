package br.com.castellan.cardmine.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class BussinessCardRepository(
    private val dao: BussinessCardDAO
) {

    fun insert(bussinessCard: BussinessCard) = runBlocking{
        launch(Dispatchers.IO) {
            dao.insert(bussinessCard)
        }
    }

    fun getAll() = dao.getAll()
}