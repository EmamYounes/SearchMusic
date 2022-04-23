package com.example.searchmusic.utilities

import com.example.instabugandroidchallenge.network.onlyLetters
import com.example.instabugandroidchallenge.ui.model.WordsCountModel
import com.example.searchmusic.pojo.MusicSearchModel

class ListUtilities {
    companion object {

        fun mapResponse(text: String): List<MusicSearchModel> {
            val values: List<String> = text.split(" ").filter {
                it.onlyLetters() && it.length > 1
            }
            return values.groupBy { it }.map {
                val item = MusicSearchModel()
                item.wordText = it.key
                item.wordCount = it.value.size.toString()
                item
            }
        }
    }
}