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
    private var _reversedTextValue = mutableStateOf("")

    var textValue = ""
        get() {
            return _textValue.value.replaceFirstChar { it.uppercase() }
        }
        set(value) {
            _textValue.value = value
            field = value
        }
    var reversedTextValue = ""
        get() {
            return _reversedTextValue.value
        }
        set(value) {
            _reversedTextValue.value = value.reversed()
            field = value.reversed()
        }

}

@Parcelize
data class GameModel(var secret: String, var guess: String, var moves: Int): Parcelable
