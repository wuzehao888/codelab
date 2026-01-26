package com.wzh.cupcake

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * create by hao
 * 2026/1/25
 */


enum class CupcakeScreen {
    Start,
    Flavor,
    Pickup,
    Summary
}

@Composable
fun CupcakeApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(modifier = modifier) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = CupcakeScreen.Start.name
        ) {
            composable(CupcakeScreen.Start.name) {

            }

            composable(CupcakeScreen.Flavor.name) {

            }
            composable(CupcakeScreen.Pickup.name) {

            }
            composable(CupcakeScreen.Summary.name) {

            }
        }
    }
}