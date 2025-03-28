package com.massimoregoli.mp2025.ui.views


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.massimoregoli.mp2025.R
import com.massimoregoli.mp2025.model.GameModel

import com.massimoregoli.mp2025.model.Model
import com.massimoregoli.mp2025.model.WordsEntity
import com.massimoregoli.mp2025.ui.theme.ContainerColor
import com.massimoregoli.mp2025.ui.theme.ContentColor

@Composable
fun InputName(modifier: Modifier = Modifier) {
    val s = rememberSaveable { mutableStateOf("") }
    val myModifier = Modifier.fillMaxWidth()
    Column(
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(modifier = myModifier, text = s.value)
        TextField(
            modifier = myModifier, value = s.value,
            onValueChange = { s.value = it })
        Button(
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp),
            onClick = { s.value = "Ciao" }) {
            Text(text = "Ok")
        }
    }
}

@Composable
fun Screen0(modifier: Modifier = Modifier) {
    val model = rememberSaveable { Model() }
    val (user, setUser) = rememberSaveable { mutableStateOf(GameModel("", "", 0)) }
    Column(modifier = modifier) {
        Screen1(model) {
            setUser(user.copy(secret = model.textValue.reversed(), moves = user.moves + 1))
        }
        Screen2(user)
    }
}

@Composable
fun Screen1(s: Model, onValueChanged: (Model) -> Unit) {
    TextField(value = s.textValue, onValueChange = {
        s.textValue = it
        onValueChanged(s)
    })
}

@Composable
fun Screen2(s: GameModel) {
    Text(text = "${s.secret} (${s.moves})")
}

val states = listOf(
    R.drawable.p1,
    R.drawable.p2,
    R.drawable.p3,
    R.drawable.p4,
    R.drawable.p5,
    R.drawable.p6,
)

@Composable
fun HangmanView(modifier: Modifier = Modifier, wordsEntity: WordsEntity, isPortrait: Boolean) {
    val state = rememberSaveable { mutableIntStateOf(0) }
    val secret = rememberSaveable { mutableStateOf(wordsEntity.getRandomWord()) }
    val attempts = rememberSaveable { mutableStateOf("") }
    val lock = rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val youWin = {
        if (!lock.value)
            Toast.makeText(context, "Win", Toast.LENGTH_SHORT).show()
        lock.value = true
    }
    val buttonClick = {
        state.intValue = 0
        attempts.value = ""
        secret.value = wordsEntity.getRandomWord()
        lock.value = false
    }

    val keyClick= { it: String ->
        if (it !in secret.value) {
            state.intValue = (state.intValue + 1)
            if (state.intValue == 5) {
                Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show()
                attempts.value = secret.value
                lock.value = true
            }
        }
        attempts.value += it
    }
    if (isPortrait) {
        Column(
            modifier = modifier.fillMaxSize().background(ContainerColor),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DrawState(state.intValue)
            SecretWord(secret.value, attempts.value, youWin)
            KeyBoard(attempts.value, lock.value, keyClick)
            ButtonBar { buttonClick() }
        }
    } else {
        Row(
            modifier = modifier.fillMaxSize().background(ContainerColor),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                DrawState(state.intValue)
                SecretWord(secret.value, attempts.value, youWin)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                KeyBoard(attempts.value, lock.value, keyClick)
                ButtonBar { buttonClick() }
            }
        }
    }
}

@Composable
fun ButtonBar(buttonClick: () -> Unit) {
    Button(
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = ContentColor,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Blue),
        onClick = buttonClick) {
        Text(text = "New")
    }

}

@Composable
fun DrawState(state: Int) {
    Row(modifier = Modifier.padding(4.dp), horizontalArrangement = Arrangement.Center) {
        Image(modifier=Modifier.
            clip(RoundedCornerShape(4.dp))
            .background(ContentColor, RoundedCornerShape(4.dp))
                    .border(1.dp, Color.Blue, RoundedCornerShape(4.dp)),
            painter = painterResource(id = states[state]),
            contentDescription = "")
    }
}

@Composable
fun SecretWord(secret: String, attempts: String, onWin: () -> Unit) {
    Row(modifier = Modifier
        .background(ContentColor, RoundedCornerShape(4.dp))
        .border(1.dp, Color.Blue, RoundedCornerShape(4.dp))
        .padding(4.dp),
        horizontalArrangement = Arrangement.Center) {
        var win = true
        secret.forEach { c ->
            if (attempts.contains(c.toString())) {
                Text(
                    text = c.toString(), modifier = Modifier.padding(2.dp),
                    fontFamily = FontFamily.Monospace,
                    fontSize = dimensionResource(R.dimen.key_size).value.sp
                )
            } else {
                win = false
                Text(
                    text = "_", modifier = Modifier.padding(2.dp),
                    fontFamily = FontFamily.Monospace,
                    fontSize = dimensionResource(R.dimen.key_size).value.sp
                )
            }
        }
        if (win) {
            onWin()
        }
    }
}

@Composable
fun KeyBoard(attempts: String, lock: Boolean, onClick: (String) -> Unit) {
    val rows = listOf("qwertyuiop", "asdfghjkl", "zxcvbnm")
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)) {
        for (row in rows) {
            Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                for (c in row) {
                    Text(
                        modifier = Modifier
                            .padding(2.dp)
                            .border(1.dp, Color.Blue, RoundedCornerShape(4.dp))
                            .padding(2.dp)
                            .background(ContentColor, RoundedCornerShape(4.dp))
                            .clickable {
                                if (!lock) {
                                    if (!attempts.contains(c.toString())) {
                                        onClick(c.toString())
                                    } else {
                                        Toast.makeText(context, "Already used", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                            .padding(2.dp),
                        text = c.toString(),
                        fontFamily = FontFamily.Monospace,
                        fontSize = dimensionResource(R.dimen.key_size).value.sp,
                        color = if (attempts.contains(c.toString())) {
                            Color(230, 230, 230, 255)
                        } else {
                            Color.Black
                        }

                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun InputNamePreview() {
    InputName()
}