package com.example.c37b.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(){
    Scaffold { padding->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
                .background(color = Color.Black)
        ) {

        }
    }
}