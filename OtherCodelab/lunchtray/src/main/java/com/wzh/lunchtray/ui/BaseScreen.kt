package com.wzh.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wzh.lunchtray.R
import com.wzh.lunchtray.model.MenuItem

/**
 * create by hao
 * 2026/1/27
 */

@Composable
fun BaseScreen(
    data: List<MenuItem>,
    onSelectedChange: (MenuItem) -> Unit,
    onCancel: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedTitle by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        LazyColumn {
            items(data) { menuItem ->
                UiItem(
                    selected = selectedTitle == menuItem.title,
                    onSelect = {
                        selectedTitle = it.title
                        onSelectedChange(it)
                    },
                    menuItem = menuItem,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }

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
                onClick = onNext,
                modifier = Modifier.weight(1f),
                enabled = selectedTitle.isNotEmpty()
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Composable
fun UiItem(
    selected: Boolean,
    onSelect: (MenuItem) -> Unit,
    menuItem: MenuItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .selectable(selected, onClick = { onSelect(menuItem) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = { onSelect(menuItem) })
        Spacer(modifier = Modifier.width(10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                menuItem.title,
                style = MaterialTheme.typography.displaySmall
            )
            Text(menuItem.desc, style = MaterialTheme.typography.bodyMedium)
            Text(
                "$${menuItem.price}",
                style = MaterialTheme.typography.bodySmall
            )
            HorizontalDivider(modifier = Modifier.height(1.dp))
        }
    }
}