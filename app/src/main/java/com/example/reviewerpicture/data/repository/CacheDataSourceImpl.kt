package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.uiModel.*

class CacheDataSourceImpl: CacheDataSource {

    private var dataList: List<AllDataUiModel> = listOf()

    override fun setData(data: List<AllDataUiModel>): List<AllDataUiModel> {
        dataList = data
        return dataList
    }

    override fun getData(): List<AllDataUiModel> {
        return dataList
    }

    override fun updateData(data: AllDataUiModel): List<AllDataUiModel> {
        return dataList.apply {
            filter { it.id == data.id }
                .forEach { currData ->
                    when(data.type){
                        ContentType.IMAGE -> {
                            (currData as ImageDataUiModel).imageLink = (data as ImageDataUiModel).imageLink
                            (currData as ImageDataUiModel).imageTaken = (data as ImageDataUiModel).imageTaken
                        }
                        ContentType.COMMENT -> {
                            (currData as CommentDataUiModel).commentText = (data as CommentDataUiModel).commentText
                            (currData as CommentDataUiModel).isEnabled = (data as CommentDataUiModel).isEnabled
                        }
                        ContentType.OPTION -> {
                            (currData as OptionsDataUiModel).selectedOption = (data as OptionsDataUiModel).selectedOption
                        }
                    }
                }
        }
    }
}