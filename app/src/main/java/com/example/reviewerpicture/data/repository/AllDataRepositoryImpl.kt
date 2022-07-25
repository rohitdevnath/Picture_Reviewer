package com.example.reviewerpicture.data.repository

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.uiModel.*
import com.example.reviewerpicture.domain.repository.Repository
import io.reactivex.Single

class AllDataRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val cacheDataSource: CacheDataSource
): Repository {

    override fun getData(): Single<List<AllDataUiModel>> {

        cacheDataSource.getData().let {
            if(it.isNotEmpty()) {
                return Single.just(it)
            }
        }

        return remoteDataSource.getData()
            .map {
                return@map mutableListOf<AllDataUiModel>().apply {
                    clear()
                    it.map<AllDataNetworkModel, Unit> {
                        add(ContentType.getUiModel(it))
                    }
                }
            }.map {
                cacheDataSource.setData(it)
            }

    }

    override fun submitData(data: List<AllDataUiModel>) {

    }

    override fun updateOptionsData(data: SingleOptionUiModel): List<AllDataUiModel> {
        return cacheDataSource.updateOptionDataInCache(data = data)
    }

    override fun updateCommentText(data: CommentDataUiModel): List<AllDataUiModel> {
        return cacheDataSource.updateCommentTextInCache(data)
    }

    override fun updateCommentToggle(data: CommentDataUiModel): List<AllDataUiModel> {
        return cacheDataSource.updateCommentToggleInCache(data)
    }

    override fun updateImageData(data: ImageDataUiModel): List<AllDataUiModel> {
        return cacheDataSource.updateImageDataInCache(data)
    }

    override fun removeImageData(data: ImageDataUiModel): List<AllDataUiModel> {
        return cacheDataSource.removeImageDataInCache(data)
    }
}