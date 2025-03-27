package com.massimoregoli.mp2025.model

import android.content.Context
import com.massimoregoli.mp2025.R
import com.massimoregoli.mp2025.readRawTextFile
import kotlin.uuid.Uuid.Companion.random

class WordsEntity(context: Context) {
    val fileContent = readRawTextFile(context, R.raw.words).split("\r\n")

    fun getRandomWord(): String {
        val ret = fileContent.random()
        return ret
    }

}