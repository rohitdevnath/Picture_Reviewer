package com.example.reviewerpicture.presentation.listingPage.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.*
import com.example.reviewerpicture.data.model.uiModel.ContentType.Companion.getTypeFromValue
import com.example.reviewerpicture.databinding.ItemCommentBinding
import com.example.reviewerpicture.databinding.ItemOptionsBinding
import com.example.reviewerpicture.databinding.ItemPhotoBinding
import com.example.reviewerpicture.interactions.Interaction

class ListingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val clickInteraction: Interaction = Interaction("")

    private val dataSet: MutableList<AllDataUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){

            ContentType.IMAGE.typeInt -> ItemPhotoBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (ListingAdapter::ImageTypeViewHolder)

            ContentType.COMMENT.typeInt -> ItemCommentBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (ListingAdapter::CommentTypeViewHolder)

            ContentType.OPTION.typeInt -> ItemOptionsBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (ListingAdapter::OptionsTypeViewHolder)

            else -> ItemPhotoBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (ListingAdapter::ImageTypeViewHolder)
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {

            ContentType.IMAGE.typeInt -> {
                (holder as ImageTypeViewHolder).bind(dataSet[position] as ImageDataUiModel)
            }

            ContentType.COMMENT.typeInt -> {
                (holder as CommentTypeViewHolder).bind(dataSet[position] as CommentDataUiModel)
            }

            ContentType.OPTION.typeInt -> {
                (holder as OptionsTypeViewHolder).bind(dataSet[position] as OptionsDataUiModel)
            }

            else -> {

            }
        }
    }

    fun setData(newData : List<AllDataNetworkModel>){

        dataSet.apply {
            clear()
            newData.map {
                val type = getTypeFromValue(it.type)
                when(type){
                    ContentType.IMAGE -> {
                        add(ImageDataUiModel(it))
                    }
                    ContentType.COMMENT -> {
                        add(CommentDataUiModel(it))
                    }
                    ContentType.OPTION -> {
                        add(OptionsDataUiModel(it))
                    }
                    else -> {

                    }

                }

            }
        }
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].type.typeInt
    }

    class ImageTypeViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(uiModel: ImageDataUiModel){
            binding.tvTitle.text = uiModel.id
        }

        companion object{
            const val interactionName : String = "OptionsTypeViewHolder"
        }

    }

    class CommentTypeViewHolder(private val binding: ItemCommentBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(uiModel: CommentDataUiModel){
            binding.tvTitle.text = uiModel.title
            binding.sth.isChecked = uiModel.isEnabled
            binding.edtText.setText(uiModel.commentText)

            if(uiModel.isEnabled){
                binding.edtText.visibility  = View.VISIBLE
            } else {
                binding.edtText.visibility  = View.GONE
            }
        }
        companion object{
            const val interactionName : String = "CommentTypeViewHolder"
        }

    }

    class OptionsTypeViewHolder(private val binding: ItemOptionsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(uiModel: OptionsDataUiModel){
            binding.tvTitle.text = uiModel.id
            binding.rvOptions.layoutManager = LinearLayoutManager(binding.root.context)
            binding.rvOptions.adapter = OptionsAdapter(uiModel.options)
        }
    }

    companion object{
        const val interactionName : String = "OptionsTypeViewHolder"
    }
}