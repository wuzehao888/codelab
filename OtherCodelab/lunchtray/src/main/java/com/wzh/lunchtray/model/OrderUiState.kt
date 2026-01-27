package com.wzh.lunchtray.model

/**
 * create by hao
 * 2026/1/27
 */
data class OrderUiState(
    val entree: MenuItem? = null,
    val sideDish: MenuItem? = null,
    val accompaniment: MenuItem? = null,
    val tax: Double = 0.08
)