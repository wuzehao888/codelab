package com.wzh.superhero.data

import com.wzh.superhero.R
import com.wzh.superhero.model.Hero

/**
 * create by hao
 * 2026/1/20
 */
class DataSource {

    fun loadHeroes(): List<Hero> {
        return listOf(
            Hero(
                name = R.string.hero1,
                desc = R.string.description1,
                image = R.drawable.android_superhero1
            ),
            Hero(
                name = R.string.hero2,
                desc = R.string.description2,
                image = R.drawable.android_superhero2
            ),
            Hero(
                name = R.string.hero3,
                desc = R.string.description3,
                image = R.drawable.android_superhero3
            ),
            Hero(
                name = R.string.hero4,
                desc = R.string.description4,
                image = R.drawable.android_superhero4
            ),
            Hero(
                name = R.string.hero5,
                desc = R.string.description5,
                image = R.drawable.android_superhero5
            ),
            Hero(
                name = R.string.hero6,
                desc = R.string.description6,
                image = R.drawable.android_superhero6
            )
        )
    }
}