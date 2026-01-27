package com.wzh.lunchtray.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wzh.lunchtray.model.MenuItem

/**
 * create by hao
 * 2026/1/27
 */

@Composable
fun EntreeScreen(
    data: List<MenuItem>,
    onSelectedChange: (MenuItem) -> Unit,
    onCancel: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseScreen(data, onSelectedChange, onCancel, onNext, modifier)
}