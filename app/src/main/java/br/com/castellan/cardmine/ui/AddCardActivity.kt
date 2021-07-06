package br.com.castellan.cardmine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.com.castellan.cardmine.App
import br.com.castellan.cardmine.R
import br.com.castellan.cardmine.data.BussinessCard
import br.com.castellan.cardmine.databinding.ActivityAddCardBinding
import br.com.castellan.cardmine.databinding.ActivityMainBinding

class AddCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory((application as App).repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.mtAddCard.setNavigationOnClickListener {
            finish()
        }

        binding.btnConfirmar.setOnClickListener{
            val card = BussinessCard(
                nome = binding.tilName.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                empresa = binding.tilCompany.editText?.text.toString(),
                fundo = binding.tilCor.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
            )

            mainViewModel.insert(card)
            Toast.makeText(this, getString(R.string.create_success), Toast.LENGTH_LONG).show()
            finish()
        }
    }
}