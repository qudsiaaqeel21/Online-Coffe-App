package com.example.onlinecoffeapp

data class Coffee(
    val id: Int,
    val name: String,
    val desc: String,
    val price: Double,
    val image: Int, // R.drawable.coffee_img
    val isFavourite: Boolean = false
)