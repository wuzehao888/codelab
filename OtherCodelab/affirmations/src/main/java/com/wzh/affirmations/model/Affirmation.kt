package com.wzh.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * create by hao
 * 2026/1/19
 */
data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)