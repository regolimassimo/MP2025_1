package com.massimoregoli.mp2025.model

import android.content.Context
import android.os.Parcelable
import android.widget.Toast
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Suppress("PropertyName")
@Parcelize
class GUIEntity(@IgnoredOnParcel val context: Context? = null) : Parcelable {
    private fun restart() {
        state = 0
        attempts = ""
        secret = wordsEntity.getRandomWord()
        lock = false
    }
    @IgnoredOnParcel
    val wordsEntity = WordsEntity(context!!)
    @IgnoredOnParcel
    val _state = mutableIntStateOf(0)
    @IgnoredOnParcel
    val _secret = mutableStateOf("")
    @IgnoredOnParcel
    val _attempts = mutableStateOf("")
    @IgnoredOnParcel
    val _lock = mutableStateOf(false)

    var state: Int
        set(value) {
            _state.intValue = value
        }
        get() {
            return _state.intValue
        }
    var secret: String
        set(value) {
            _secret.value = value
        }
        get() {
            return _secret.value
        }
    var attempts: String
        set(value) {
            _attempts.value = value
        }
        get() {
            return _attempts.value
        }
    var lock: Boolean
        set(value) {
            _lock.value = value
        }
        get() {
            return _lock.value
        }
    init {
        restart()
    }

    @IgnoredOnParcel
    val youWin = {
        if (!lock)
            Toast.makeText(context, "Win", Toast.LENGTH_SHORT).show()
        lock = true
    }

    @IgnoredOnParcel
    val buttonClick = {
        restart()
    }

    @IgnoredOnParcel
    val keyClick = { it: String ->
        if (it !in secret) {
            state = (state + 1)
            if (state == 5) {
                Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show()
                attempts = secret
                lock = true
            }
        }
        attempts += it
    }


}