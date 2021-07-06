package br.com.castellan.cardmine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.castellan.cardmine.R
import br.com.castellan.cardmine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}