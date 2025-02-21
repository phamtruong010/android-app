package com.helloworld.ui.feed

data class FeedUiState(
    val showLoading: Boolean = true,
    val errorMessage: String? = null,
)

sealed class FeedNavigationState {
    data class MovieDetails(val movieId: Int) : FeedNavigationState()
}
