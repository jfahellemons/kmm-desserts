package com.example.justdesserts.shared.cache

import com.example.justdesserts.GetDessertQuery
import com.example.justdesserts.GetDessertsQuery
import com.example.justdesserts.GetProfileQuery
import com.example.justdesserts.NewDessertMutation
import com.example.justdesserts.UpdateDessertMutation

// 1
data class Desserts(val results: List<Dessert>, val info: GetDessertsQuery.Info?)
data class DessertDetail(val dessert: Dessert, val reviews: List<Review>)

// 2
fun GetDessertsQuery.Result.toDessert() = Dessert(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun GetDessertsQuery.Desserts.toDesserts() = Desserts(
    results = results.map {
        it.toDessert()
    },
    info = info
)

fun GetDessertQuery.Dessert.toDessert() = Dessert(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun GetDessertQuery.Dessert.toDessertDetail() = DessertDetail(
    dessert = this.toDessert(),
    reviews = emptyList(),
)

fun GetProfileQuery.Dessert.toDessert() = Dessert(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun NewDessertMutation.CreateDessert.toDessert() = Dessert(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)
fun UpdateDessertMutation.UpdateDessert.toDessert() = Dessert(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)
