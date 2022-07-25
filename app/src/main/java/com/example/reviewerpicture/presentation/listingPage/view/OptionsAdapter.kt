package com.example.reviewerpicture.presentation.listingPage.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewerpicture.data.model.uiModel.*
import com.example.reviewerpicture.databinding.ItemSingleOptionBinding
import com.example.reviewerpicture.presentation.interactions.Interaction
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_OptionClicked
import io.reactivex.subjects.PublishSubject

class OptionsAdapter(private val dataSet: List<SingleOptionUiModel>,
                     private val clickInteractions: PublishSubject<Interaction>)
    : RecyclerView.Adapter<OptionsAdapter.SingleOptionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleOptionViewHolder =
        ItemSingleOptionBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (::SingleOptionViewHolder)

    override fun onBindViewHolder(holder: SingleOptionViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    inner class SingleOptionViewHolder(private val binding: ItemSingleOptionBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(uiModel: SingleOptionUiModel){
            binding.tvOption.text = uiModel.OptionText
            binding.rbOption.isChecked = uiModel.isSelected
            binding.rbOption.setOnClickListener {
                clickInteractions.onNext(
                    Interaction(type = INTERACTION_OptionClicked, entity = uiModel.copy(
                        isSelected = uiModel.isSelected.not()
                     )
                )
                )
            }
        }
    }

}