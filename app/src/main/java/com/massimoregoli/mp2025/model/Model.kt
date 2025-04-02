package com.massimoregoli.mp2025.model

import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class Model: Parcelable {
    @IgnoredOnParcel
    private var _textValue = mutableStateOf("")
    @IgnoredOnParcel
//    private var _reversedTextValue = mutableStateOf("")

    var textValue: String
        set(value) {
            _textValue.value = value
        }
        get() {
            return _textValue.value.replaceFirstChar { it.uppercase()}
        }

//    var reversedTextValue: String
//        set(value) {
//            _reversedTextValue.value = value.reversed()
//        }
//        get() {
//            return _textValue.value.replaceFirstChar { it.uppercase()}
//        }
}

@Parcelize
data class GameModel(var secret: String, var guess: String, var moves: Int): Parcelable
