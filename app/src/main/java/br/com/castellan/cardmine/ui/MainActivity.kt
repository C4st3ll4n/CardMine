package br.com.castellan.cardmine.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.castellan.cardmine.App
import br.com.castellan.cardmine.R
import br.com.castellan.cardmine.databinding.ActivityMainBinding
import br.com.castellan.cardmine.ui.adapter.BussinessAdapter
import br.com.castellan.cardmine.util.Image
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory((application as App).repository) }
    private val adapter:BussinessAdapter by lazy{BussinessAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBussinessCard()
        setupListeners()
    }

    private fun setupListeners() {

        adapter.listenerShare = {
            Image().share(this, it)
        }

        binding.fabNewCard.setOnClickListener{
            startActivity(Intent(this@MainActivity,AddCardActivity::class.java))
        }
    }

    private fun getAllBussinessCard(){
        mainViewModel.getAll().observe(this,{
            bussinessCards -> adapter.submitList(bussinessCards)
        })
    }
}

