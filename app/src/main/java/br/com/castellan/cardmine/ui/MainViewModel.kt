package br.com.castellan.cardmine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.castellan.cardmine.data.BussinessCard
import br.com.castellan.cardmine.data.BussinessCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(private  val bussinessCardRepository: BussinessCardRepository): ViewModel() {
    fun insert(bussinessCard: BussinessCard){
        bussinessCardRepository.insert(bussinessCard)
    }

    fun getAll(): LiveData<List<BussinessCard>>{
        return bussinessCardRepository.getAll()
    }
}

class MainViewModelFactory(private val repository: BussinessCardRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }

        throw  IllegalArgumentException("unknow view model")
    }

}