package com.example.reviewerpicture.presentation.listingPage.view

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewerpicture.data.model.uiModel.*
import com.example.reviewerpicture.databinding.ItemCommentBinding
import com.example.reviewerpicture.databinding.ItemOptionsBinding
import com.example.reviewerpicture.databinding.ItemPhotoBinding
import com.example.reviewerpicture.presentation.interactions.Interaction
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_AddImage
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_CommentChanged
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_CommentToggled
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_EnlargeImage
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_RemoveImage
import io.reactivex.subjects.PublishSubject

class ListingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val clickInteractions: PublishSubject<Interaction> by lazy {
        PublishSubject.create()
    }

    private val dataSet: MutableList<AllDataUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){

            ContentType.IMAGE.typeInt -> ItemPhotoBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (::ImageTypeViewHolder)

            ContentType.COMMENT.typeInt -> ItemCommentBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (::CommentTypeViewHolder)

            ContentType.OPTION.typeInt -> ItemOptionsBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (::OptionsTypeViewHolder)

            else -> ItemPhotoBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
                .run (::ImageTypeViewHolder)
        }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {

            ContentType.IMAGE.typeInt -> {
                (holder as ImageTypeViewHolder).bind(dataSet[position] as ImageDataUiModel)
            }

            ContentType.COMMENT.typeInt -> {
                (holder as CommentTypeViewHolder).bind(dataSet[position] as CommentDataUiModel)
                    .also { holder.setIsRecyclable(false) }
            }

            ContentType.OPTION.typeInt -> {
                (holder as OptionsTypeViewHolder).bind(dataSet[position] as OptionsDataUiModel)
            }

            else -> {

            }
        }
    }

    fun setData(newData : List<AllDataUiModel>, notify: Boolean){
        dataSet.apply {
            clear()
            addAll(newData)
        }

        if(notify) notifyDataSetChanged()
    }


    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].type.typeInt
    }

    inner class ImageTypeViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(uiModel: ImageDataUiModel){
            binding.tvTitle.text = uiModel.id
            binding.ivImage.setImageBitmap(uiModel.imageLink)
            if(uiModel.imageTaken){
                binding.ivAddImage.visibility  = View.VISIBLE
                binding.ivAddImage.visibility  = View.GONE
                binding.ivRemoveIcon.visibility  = View.VISIBLE
            } else {
                binding.ivAddImage.visibility  = View.INVISIBLE
                binding.ivAddImage.visibility  = View.VISIBLE
                binding.ivRemoveIcon.visibility  = View.GONE
            }

            binding.ivRemoveIcon.setOnClickListener {
                clickInteractions.onNext(
                    Interaction(type = INTERACTION_RemoveImage, entity = uiModel)
                )
            }

            binding.ivAddImage.setOnClickListener {
                clickInteractions.onNext(Interaction(type = INTERACTION_AddImage, entity = uiModel))
            }

            binding.ivImage.setOnClickListener {
                clickInteractions.onNext(
                    Interaction(type = INTERACTION_EnlargeImage, entity = uiModel)
                )
            }
        }

    }

    inner class CommentTypeViewHolder(private val binding: ItemCommentBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(uiModel: CommentDataUiModel){
            binding.tvTitle.text = uiModel.title
            binding.sth.isChecked = uiModel.isEnabled
            binding.edtText.setText(uiModel.commentText)
            binding.sth.setOnCheckedChangeListener { compoundButton, b ->
                clickInteractions.onNext(
                    Interaction(type = INTERACTION_CommentToggled,
                        entity = uiModel.copy(isEnabled = b)
                    )
                )
            }

            binding.edtText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    s?.let{

                        if((uiModel.commentText == it.toString()).not()) {
                            clickInteractions.onNext(
                                Interaction(type = INTERACTION_CommentChanged,
                                    entity = uiModel.copy(commentText = it.toString())))
                        }
                    }
                }
            })

            if(uiModel.isEnabled){
                binding.edtText.visibility  = View.VISIBLE
            } else {
                binding.edtText.visibility  = View.GONE
            }
        }
    }

    inner class OptionsTypeViewHolder(private val binding: ItemOptionsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(uiModel: OptionsDataUiModel){
            binding.tvTitle.text = uiModel.id
            binding.rvOptions.layoutManager = LinearLayoutManager(binding.root.context)
            binding.rvOptions.adapter = OptionsAdapter(uiModel.options,clickInteractions = clickInteractions)
        }
    }
}