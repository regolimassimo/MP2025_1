package com.massimoregoli.mp2025.model

import android.content.Context
import com.massimoregoli.mp2025.R
import com.massimoregoli.mp2025.readRawTextFile

class WordsEntity(context: Context) {
    private val fileContent = readRawTextFile(context, R.raw.words).split("\r\n")

    fun getRandomWord(): String {
        val ret = fileContent.random()
        return ret
    }

}