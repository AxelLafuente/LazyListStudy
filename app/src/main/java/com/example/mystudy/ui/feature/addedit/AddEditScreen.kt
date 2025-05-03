package com.example.mystudy.ui.feature.addedit

import RadialGradientScaffold
import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mystudy.data.TodoDatabaseProvider
import com.example.mystudy.data.TodoRepositoryImpl
import com.example.mystudy.ui.UiEvent
import com.example.mystudy.ui.theme.MyStudyTheme

@Composable
fun AddEditScreen(
    id: Long?,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val dataBase = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(dataBase.dao)

    val viewModel = viewModel<AddEditViewModel>()
    {
        AddEditViewModel(
            repository = repository,
            id = id)
    }

    val title = viewModel.title
    val description = viewModel.description

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> TODO()

                UiEvent.NavigateBack -> navigateBack()

                is UiEvent.ShowSnackbar -> snackbarHostState.showSnackbar(
                    message = uiEvent.mesage,
                )
            }
        }
    }

    AddEditContent(title, description, viewModel::onEvent, snackbarHostState)
}


@Composable
fun AddEditContent(
    title: String,
    description: String?,
    onEvent: (AddEditEvent) -> Unit = {},
    snackbarHostState: SnackbarHostState
) {
    RadialGradientScaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            onEvent(AddEditEvent.Save)
        }) {
            Icon(Icons.Default.Check, contentDescription = "save")
        }
    },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .consumeWindowInsets(it)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = {
                    onEvent(AddEditEvent.TitleChanged(it))
                },
                placeholder = {
                    Text(text = "Title*")
                })

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = description ?: "",
                onValueChange = { onEvent(AddEditEvent.DescriptionChanged(it)) },
                placeholder = {
                    Text(text = "Desc")
                })

        }

    }

}


@Preview
@Composable
private fun AddEditContentPreview() {
    MyStudyTheme {
        AddEditContent("", null, {}, snackbarHostState = SnackbarHostState())
    }

}