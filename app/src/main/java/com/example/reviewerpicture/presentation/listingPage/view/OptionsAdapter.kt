package com.example.reviewerpicture.presentation.listingPage.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewerpicture.data.model.uiModel.*
import com.example.reviewerpicture.databinding.ItemSingleOptionBinding
import com.example.reviewerpicture.interactions.Interaction

class OptionsAdapter(private val dataSet: List<SingleOptionUiModel>)
    : RecyclerView.Adapter<OptionsAdapter.SingleOptionViewHolder>(){

    val clickInteraction: Interaction = Interaction("")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleOptionViewHolder =
        ItemSingleOptionBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (::SingleOptionViewHolder)

    override fun onBindViewHolder(holder: SingleOptionViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    class SingleOptionViewHolder(private val binding: ItemSingleOptionBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(uiModel: SingleOptionUiModel){
            binding.tvOption.text = uiModel.OptionText
            binding.rbOption.isChecked = uiModel.isSelected
        }

        companion object{
            const val interactionName : String = "OptionsTypeViewHolder"
        }

    }

}