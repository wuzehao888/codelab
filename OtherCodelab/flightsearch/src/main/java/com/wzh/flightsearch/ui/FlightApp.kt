package com.wzh.flightsearch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * create by hao
 * 2026/2/9
 */


@Composable
fun FlightScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(20.dp)) {
        TextField(
            value = input,
            onValueChange = {
                input = it
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Cyan,
                unfocusedContainerColor = Color.Cyan,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        ContentBody(
            input, modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun ContentBody(content: String, modifier: Modifier = Modifier) {
    if (content.isEmpty()) {
        FavoriteScreen(modifier)
    } else {
        FlightListScreen(modifier)
    }
}

@Composable
fun FlightListScreen(modifier: Modifier) {
    var isShowDetail by remember { mutableStateOf(false) }
    if (isShowDetail) {

    } else {

    }
}

@Composable
fun FavoriteScreen(modifier: Modifier) {

}