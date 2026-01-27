package com.wzh.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wzh.lunchtray.R
import com.wzh.lunchtray.model.MenuItem
import com.wzh.lunchtray.model.OrderUiState

/**
 * create by hao
 * 2026/1/27
 */

@Composable
fun OrderCheckout(
    orderUiState: OrderUiState,
    onCancel: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Order Summary", fontWeight = FontWeight.Bold)
        DetailItem(orderUiState.entree)
        DetailItem(orderUiState.sideDish)
        DetailItem(orderUiState.accompaniment)

        HorizontalDivider(modifier = Modifier.height(1.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {
            OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.cancel))
            }

            Spacer(modifier = Modifier.weight(0.2f))

            Button(
                onClick = onFinish,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.finish))
            }
        }
    }
}

@Composable
fun DetailItem(menuItem: MenuItem?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(menuItem?.title ?: "")
        Text("$${menuItem?.price ?: 0}")
    }
}