package com.example.mystudy.ui.feature.list

import RadialGradientScaffold
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mystudy.data.TodoDatabaseProvider
import com.example.mystudy.data.TodoRepositoryImpl
import com.example.mystudy.domain.Todo
import com.example.mystudy.domain.todo1
import com.example.mystudy.domain.todo2
import com.example.mystudy.domain.todo3
import com.example.mystudy.navigation.AddEditRoute
import com.example.mystudy.ui.UiEvent
import com.example.mystudy.ui.components.TodoItem
import com.example.mystudy.ui.theme.Jost
import com.example.mystudy.ui.theme.MyStudyTheme
import com.example.mystudy.ui.theme.TextBlack
import com.example.mystudy.ui.theme.White

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit
) {
    val context = LocalContext.current.applicationContext
    val dataBase = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(dataBase.dao)

    val viewModel = viewModel<ListViewModel>()
    {
        ListViewModel(repository)
    }


    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> {
                    when (uiEvent.route) {
                        is AddEditRoute -> {
                            navigateToAddEditScreen(uiEvent.route.id)
                        }
                    }
                }

                UiEvent.NavigateBack -> TODO()
                is UiEvent.ShowSnackbar -> TODO()
            }
        }
    }

    ListContent(
        todos = todos,
        onEvent = viewModel::onEvent
    )

}

@Composable
fun ListContent(
    todos: List<Todo>,
    onEvent: (ListEvent) -> Unit
) {
    RadialGradientScaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            onEvent(ListEvent.AddEdit(null))
        }) {

            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
    )
    {
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Text(
                    modifier = Modifier.padding(vertical = 24.dp),
                    text = "Lista de tarefas de hoje",
                    style = TextStyle(
                        fontFamily = Jost,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp,
                        color = TextBlack
                    ),
                    textDecoration = TextDecoration.Underline
                )
            }
            itemsIndexed(todos) { index, todo ->
                TodoItem(todo = todo,
                    onCompletedChange = {
                        onEvent(ListEvent.Complete(todo.id, it))
                    },
                    onItemClick = {
                        onEvent(ListEvent.AddEdit(todo.id))
                    },
                    onDeleteClick = {
                        onEvent(ListEvent.Delete(todo.id))
                    })
                if (index < todos.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }


}

@Preview
@Composable
private fun ListContentPreview() {
    MyStudyTheme {
        ListContent(
            todos = listOf(todo1, todo2, todo3),
            onEvent = {}
        )
    }
}