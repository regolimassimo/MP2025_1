package com.massimoregoli.mp2025.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class Page(val title:String, val content: String){
    HOME("Home","Show only icon"),
    SEARCH("Search","Show icon with label"),
    SETTINGS("Settings","Show icon /Show the label only when selected")
}

@Composable
fun NavigationRailSample(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }
    val pages = Page.entries.toTypedArray()
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)
    Row(modifier=modifier) {
        NavigationRail(header = { Text("Header", color= Color.Red) },) {
            pages.forEachIndexed { index, item ->
                when(item){
                    Page.HOME -> {
                        NavigationRailItem(
                            label = { Text(item.title) },
                            icon = { Icon(icons[index], contentDescription = "") },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                    Page.SEARCH -> {
                        NavigationRailItem(
                            label = { Text(item.title) },
                            icon = { Icon(icons[index], contentDescription = "") },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                    Page.SETTINGS -> {
                        NavigationRailItem(
                            label = { Text(item.title) },
                            icon = { Icon(icons[index], contentDescription = "") },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                            alwaysShowLabel = false
                        )
                    }
                }
            }
        }

        Text(pages[selectedItem].content, Modifier.padding(start = 8.dp))
    }
}