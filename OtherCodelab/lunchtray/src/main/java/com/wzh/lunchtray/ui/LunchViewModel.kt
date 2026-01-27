package com.wzh.lunchtray.ui

import androidx.lifecycle.ViewModel
import com.wzh.lunchtray.model.MenuItem
import com.wzh.lunchtray.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * create by hao
 * 2026/1/27
 */
class LunchViewModel : ViewModel() {

    private val _orderUiState = MutableStateFlow(OrderUiState())

    val orderUiState: StateFlow<OrderUiState> = _orderUiState.asStateFlow()

    private var entreeItem: MenuItem? = null
    private var sideItem: MenuItem? = null
    private var accompanimentItem: MenuItem? = null

    fun setEntree(entreeItem: MenuItem) {
        this.entreeItem = entreeItem
        _orderUiState.update {
            it.copy(entree = entreeItem)
        }
    }

    fun setSideDish(sideItem: MenuItem) {
        this.sideItem = sideItem
        _orderUiState.update {
            it.copy(sideDish = sideItem)
        }
    }

    fun setAccompaniment(accompanimentItem: MenuItem) {
        this.accompanimentItem = accompanimentItem
        _orderUiState.update {
            it.copy(accompaniment = accompanimentItem)
        }
    }

    fun resetOrder() {
        _orderUiState.value = OrderUiState()
    }
}