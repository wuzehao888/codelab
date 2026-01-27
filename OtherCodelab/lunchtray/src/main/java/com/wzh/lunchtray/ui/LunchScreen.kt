package com.wzh.lunchtray.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wzh.lunchtray.R
import com.wzh.lunchtray.data.DataSource

/**
 * create by hao
 * 2026/1/27
 */

enum class LunchScreen(@StringRes val title: Int) {
    Start(R.string.start_order),
    Entree(R.string.choose_entree),
    SideDish(R.string.choose_side_dish),
    Accompaniment(R.string.choose_accompaniment),
    Account(R.string.order_checkout)
}

@Composable
fun LunchApp(
    navController: NavHostController = rememberNavController(),
    viewModel: LunchViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val backStackEntryAsState by
    navController.currentBackStackEntryAsState()
    val currentScreen = LunchScreen.valueOf(
        backStackEntryAsState?.destination?.route
            ?: LunchScreen.Start.name
    )

    val orderUiState by
    viewModel.orderUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            LunchAppbar(
                currentScreen = currentScreen,
                showBackIcon = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = LunchScreen.Start.name
        ) {
            composable(LunchScreen.Start.name) {
                StartOrderScreen(onStartOrderClick = {
                    navController.navigate(LunchScreen.Entree.name)
                })
            }

            composable(LunchScreen.Entree.name) {
                EntreeScreen(
                    DataSource.entreeMenuItems,
                    onSelectedChange = {
                        viewModel.setEntree(it)
                    },
                    onCancel = {
                        navController.cancelBackStart()
                    },
                    onNext = {
                        navController.navigate(LunchScreen.SideDish.name)
                    })
            }

            composable(LunchScreen.SideDish.name) {
                SideDishScreen(
                    DataSource.sideDishMenuItems,
                    onSelectedChange = {
                        viewModel.setSideDish(it)
                    }, onCancel = {
                        navController.cancelBackStart()
                    },
                    onNext = {
                        navController.navigate(LunchScreen.Accompaniment.name)
                    })
            }

            composable(LunchScreen.Accompaniment.name) {
                SideDishScreen(
                    DataSource.accompanimentMenuItems,
                    onSelectedChange = {
                        viewModel.setAccompaniment(it)
                    }, onCancel = {
                        navController.cancelBackStart()
                    },
                    onNext = {
                        navController.navigate(LunchScreen.Account.name)
                    })
            }

            composable(LunchScreen.Account.name) {
                OrderCheckout(
                    orderUiState = orderUiState,
                    onCancel = {
                        navController.cancelBackStart()
                    },
                    onFinish = {
                        viewModel.resetOrder()
                        navController.cancelBackStart()
                    }
                )
            }
        }
    }
}

private fun NavHostController.cancelBackStart() {
    this.popBackStack(
        LunchScreen.Start.name,
        inclusive = false
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchAppbar(
    currentScreen: LunchScreen,
    showBackIcon: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                stringResource(currentScreen.title),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}