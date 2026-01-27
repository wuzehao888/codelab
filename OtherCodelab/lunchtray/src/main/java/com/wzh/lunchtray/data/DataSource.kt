package com.wzh.lunchtray.data

import com.wzh.lunchtray.model.MenuItem

/**
 * create by hao
 * 2026/1/27
 */

object DataSource{
    val entreeMenuItems = listOf(
        MenuItem(
            title = "Cauliflower",
            desc = "Whole cauliflower, brined, roasted, and deep fried",
            price = 7.00,
        ),
        MenuItem(
            title = "Three Bean Chili",
            desc = "Black beans, red beans, kidney beans, slow cooked, topped with onion",
            price = 4.00,
        ),
        MenuItem(
            title = "Mushroom Pasta",
            desc = "Penne pasta, mushrooms, basil, with plum tomatoes cooked in garlic " +
                    "and olive oil",
            price = 5.50,
        ),
        MenuItem(
            title = "Spicy Black Bean Skillet",
            desc = "Seasonal vegetables, black beans, house spice blend, served with " +
                    "avocado and quick pickled onions",
            price = 5.50,
        )
    )

    val sideDishMenuItems = listOf(
        MenuItem(
            title = "Summer Salad",
            desc = "Heirloom tomatoes, butter lettuce, peaches, avocado, balsamic dressing",
            price = 2.50,
        ),
        MenuItem(
            title = "Butternut Squash Soup",
            desc = "Roasted butternut squash, roasted peppers, chili oil",
            price = 3.00,
        ),
        MenuItem(
            title = "Spicy Potatoes",
            desc = "Marble potatoes, roasted, and fried in house spice blend",
            price = 2.00,
        ),
        MenuItem(
            title = "Coconut Rice",
            desc = "Rice, coconut milk, lime, and sugar",
            price = 1.50,
        )
    )

    val accompanimentMenuItems = listOf(
        MenuItem(
            title = "Lunch Roll",
            desc = "Fresh baked roll made in house",
            price = 0.50,
        ),
        MenuItem(
            title = "Mixed Berries",
            desc = "Strawberries, blueberries, raspberries, and huckleberries",
            price = 1.00,
        ),
        MenuItem(
            title = "Pickled Veggies",
            desc = "Pickled cucumbers and carrots, made in house",
            price = 0.50,
        )
    )
}