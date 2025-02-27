package com.helloworld.presentation.navigation


import kotlinx.serialization.Serializable

sealed class Page {
    @Serializable
    data object NavigationBar : Page()

    @Serializable
    data object Overview : Page()

    @Serializable
    data object Feed : Page()

    @Serializable
    data object Favorites : Page()

    @Serializable
    data object Portfolio : Page()

    @Serializable
    data object Search : Page()

    @Serializable
    data class MovieDetails(val movieId: Int) : Page()
}

sealed class Graph {
    @Serializable
    data object Main : Graph()

    @Serializable
    data object home : Graph()
}

fun Page.route(): String? {
    return this.javaClass.canonicalName
}