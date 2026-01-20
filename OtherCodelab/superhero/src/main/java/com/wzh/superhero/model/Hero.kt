package com.wzh.superhero.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * create by hao
 * 2026/1/20
 */
data class Hero(
    @StringRes val name: Int,
    @StringRes val desc: Int,
    @DrawableRes val image: Int
)