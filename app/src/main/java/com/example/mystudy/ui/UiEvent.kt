package com.example.mystudy.ui

sealed interface UiEvent{
    data class ShowSnackbar(val mesage: String) : UiEvent
    data object NavigateBack : UiEvent
    data class Navigate<T: Any>(val route: T) : UiEvent
}