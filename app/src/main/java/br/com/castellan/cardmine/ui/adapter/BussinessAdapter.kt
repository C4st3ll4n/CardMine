package br.com.castellan.cardmine.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.castellan.cardmine.data.BussinessCard
import br.com.castellan.cardmine.databinding.BussinesCardBinding
import br.com.castellan.cardmine.ui.adapter.BussinessAdapter.BussinessVH

class BussinessAdapter : ListAdapter<BussinessCard, BussinessVH>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    inner class BussinessVH
        (private val binding: BussinesCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BussinessCard?) {

            binding.tvCompany.text = item?.empresa
            binding.tvNome.text = item?.nome
            binding.tvEmail.text = item?.email
            binding.tvTelefone.text = item?.telefone

            binding.bussinesCard.setBackgroundColor(Color.parseColor(item?.fundo))
            binding.bussinesCard.setOnClickListener {
                listenerShare(it)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BussinessVH {
        val binding = BussinesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BussinessVH(binding)
    }

    override fun onBindViewHolder(holder: BussinessVH, position: Int) {
        holder.bind(getItem(position))
    }

}

class DiffCallback : DiffUtil.ItemCallback<BussinessCard>() {
    override fun areItemsTheSame(oldItem: BussinessCard, newItem: BussinessCard): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: BussinessCard, newItem: BussinessCard): Boolean = oldItem.id == newItem.id

}
