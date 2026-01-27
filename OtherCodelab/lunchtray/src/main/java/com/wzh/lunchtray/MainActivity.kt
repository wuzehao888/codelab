package com.wzh.lunchtray

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wzh.lunchtray.ui.LunchApp
import com.wzh.lunchtray.ui.theme.OtherCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtherCodelabTheme {
                LunchApp()
            }
        }
    }
}