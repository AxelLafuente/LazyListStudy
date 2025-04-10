package com.example.mystudy.ui.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystudy.data.TodoRepository
import com.example.mystudy.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val id: Long? = null,
    private val repository: TodoRepository
) : ViewModel() {
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent  = _uiEvent.receiveAsFlow()

    init {
        id?.let {
            viewModelScope.launch {
                val todo = repository.getById(it)
                title = todo?.title ?: ""
                description = todo?.description ?: ""
            }
        }
    }


    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.TitleChanged -> {
                title = event.title
            }

            is AddEditEvent.DescriptionChanged -> {
                description = event.description
            }

            AddEditEvent.Save -> {
                saveTodo()
            }
        }
    }

    private fun saveTodo(){
        viewModelScope.launch {
            if (title.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("Title can't be empty"))
                return@launch
            }
            repository.insert(title, description, id)
            _uiEvent.send(UiEvent.NavigateBack)
        }

    }

}