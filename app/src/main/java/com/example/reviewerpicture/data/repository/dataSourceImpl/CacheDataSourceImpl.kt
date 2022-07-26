package com.example.reviewerpicture.data.repository.dataSourceImpl

import com.example.reviewerpicture.data.model.uiModel.*
import com.example.reviewerpicture.data.repository.dataSource.CacheDataSource
import com.example.reviewerpicture.utils.emptyString
import com.example.reviewerpicture.utils.getThumbnailImage

class CacheDataSourceImpl : CacheDataSource {

    private val dataList: MutableList<AllDataUiModel> = mutableListOf()

    override fun setData(data: List<AllDataUiModel>): List<AllDataUiModel> {
        dataList.apply {
            clear()
            addAll(data)
        }
        return dataList
    }

    override fun getData(): List<AllDataUiModel> {
        return dataList
    }

    override fun updateOptionDataInCache(data: SingleOptionUiModel): List<AllDataUiModel> {
        return dataList.apply {

            replaceAll {
                if (it is OptionsDataUiModel && it.id == data.parentId) {
                    it.copy(options = it.options.map { sOp ->
                        sOp.copy(isSelected = if (sOp.OptionText == data.OptionText) data.isSelected else false)
                    })
                } else {
                    it
                }
            }
        }

    }

    override fun updateCommentTextInCache(data: CommentDataUiModel): List<AllDataUiModel> {
        return dataList.apply {
            replaceAll {
                if (it is CommentDataUiModel && it.id == data.id) {
                    data.copy(commentText = data.commentText)
                } else {
                    it
                }
            }
        }
    }

    override fun updateCommentToggleInCache(data: CommentDataUiModel): List<AllDataUiModel> {
        return dataList.apply {
            replaceAll {
                if (it is CommentDataUiModel && it.id == data.id) {
                    data.copy(isEnabled = data.isEnabled)
                } else {
                    it
                }
            }
        }

    }

    override fun updateImageDataInCache(data: ImageDataUiModel): List<AllDataUiModel> {
        return dataList.apply {
            replaceAll {
                if (it is ImageDataUiModel && it.id == data.id) {
                    data.copy(
                        imagePath = data.imagePath,
                        imageTaken = true,
                        imageLink = getThumbnailImage(data.imagePath)
                    )
                } else {
                    it
                }
            }
        }
    }

    override fun removeImageDataInCache(data: ImageDataUiModel): List<AllDataUiModel> {
        return dataList.apply {
            replaceAll {
                if (it is ImageDataUiModel && it.id == data.id) {
                    data.copy(imagePath = emptyString(), imageTaken = false, imageLink = null)
                } else {
                    it
                }
            }
        }
    }
}