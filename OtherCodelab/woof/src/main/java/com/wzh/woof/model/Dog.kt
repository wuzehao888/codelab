package com.wzh.woof.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * create by hao
 * 2026/1/19
 */
data class Dog(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)