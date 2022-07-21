package com.example.reviewerpicture.data.model.uiModel


enum class ContentType(val type: String, val typeInt: Int) {
    IMAGE("PHOTO", 1),
    COMMENT("COMMENT", 2),
    OPTION("SINGLE_CHOICE", 3),
    UNKNOWN("", 0);

    companion object {

        fun getTypeFromValue(type: String): ContentType = when (type) {
            IMAGE.type -> IMAGE
            COMMENT.type -> COMMENT
            OPTION.type -> OPTION
            else -> UNKNOWN
        }
    }
}