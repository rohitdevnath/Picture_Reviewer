package com.example.reviewerpicture.presentation.interactions

data class Interaction(
    val type: Int,
    val entity: Any = Any()
){
    companion object{
        const val INTERACTION_AddImage : Int = 0
        const val INTERACTION_RemoveImage : Int = 1
        const val INTERACTION_EnlargeImage : Int = 2
        const val INTERACTION_OptionClicked : Int = 3
        const val INTERACTION_CommentToggled : Int = 4
        const val INTERACTION_CommentChanged : Int = 5
    }
}