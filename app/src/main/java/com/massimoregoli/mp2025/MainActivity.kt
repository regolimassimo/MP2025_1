package com.massimoregoli.mp2025

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.massimoregoli.mp2025.model.WordsEntity
import com.massimoregoli.mp2025.ui.theme.MP2025Theme
import com.massimoregoli.mp2025.ui.views.HangmanView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            MP2025Theme {
                val wordsEntity = WordsEntity(this)
                val insetsController = WindowCompat.getInsetsController(window, window.decorView)
                insetsController.apply {
                    hide(WindowInsetsCompat.Type.statusBars())
                    hide(WindowInsetsCompat.Type.navigationBars())
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
                val configuration = LocalConfiguration.current
                val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = Color(136, 161, 184, 255),
                    topBar = { Text("Hangman",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(8.dp)) },) { innerPadding ->
                    HangmanView(modifier = Modifier.padding(innerPadding), wordsEntity, isPortrait)
//                NavigationRailSample(Modifier.padding(innerPadding))
//                    Screen0(modifier = Modifier.padding(innerPadding))
//                    InputName(modifier = Modifier.padding(innerPadding))
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MP2025Theme {
        Greeting("Android")
    }
}

fun readRawTextFile(context: Context, rawResId: Int): String {
    return context.resources.openRawResource(rawResId).bufferedReader().use { it.readText() }
}