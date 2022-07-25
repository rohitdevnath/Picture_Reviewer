package com.example.reviewerpicture.data.model.uiModel

import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel.Companion.ARG_TYPE_COMMENT
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel.Companion.ARG_TYPE_OPTIONS
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel.Companion.ARG_TYPE_PHOTO
import com.example.reviewerpicture.utils.emptyString
import java.lang.IllegalArgumentException


enum class ContentType(val type: String, val typeInt: Int) {
    IMAGE( ARG_TYPE_PHOTO, 1),
    COMMENT( ARG_TYPE_COMMENT, 2),
    OPTION( ARG_TYPE_OPTIONS, 3),
    UNKNOWN(emptyString(), 0);

    companion object {

        private fun getTypeFromValue(type: String): ContentType = when (type) {
            IMAGE.type -> IMAGE
            COMMENT.type -> COMMENT
            OPTION.type -> OPTION
            else -> UNKNOWN
        }

        fun getUiModel(networkModel: AllDataNetworkModel): AllDataUiModel{
            return when(val type = getTypeFromValue(networkModel.type)) {
                IMAGE -> {
                    ImageDataUiModel(networkModel,type)
                }
                COMMENT -> {
                    CommentDataUiModel(networkModel,type)
                }
                OPTION -> {
                    OptionsDataUiModel(networkModel, type)
                }
                else -> {
                    throw IllegalArgumentException("${networkModel.type} is not a valid data type")
                }
            }
        }
    }
}