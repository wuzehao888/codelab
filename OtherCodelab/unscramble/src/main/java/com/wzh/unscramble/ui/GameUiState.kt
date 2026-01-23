package com.wzh.unscramble.ui

/**
 * create by hao
 * 2026/1/23
 */
data class GameUiState(
    val currentWord: String = "",
    val inputError: Boolean = false,
    val rightNum: Int = 1,
    val score: Int = 0,
    val finish: Boolean = false
)