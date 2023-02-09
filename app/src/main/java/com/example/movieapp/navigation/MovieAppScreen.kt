package com.example.movieapp.navigation

enum class MovieAppScreen {
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRouter(router: String?): MovieAppScreen = when (router?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalAccessException("Router $router, is not recognize")
        }
    }
}