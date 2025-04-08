package com.example.mystudy.ui.components

import android.provider.CalendarContract
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystudy.domain.Todo
import com.example.mystudy.domain.todo2
import com.example.mystudy.ui.theme.MyStudyTheme
import com.example.mystudy.ui.theme.TodoCompletedColor
import com.example.mystudy.ui.theme.TodoIncompleteColor

@Composable
fun TodoItem(
    onCompletedChange: (Boolean) -> Unit,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    todo: Todo,
) {
    val backgroundColor = if (todo.isCompleted) {
        TodoCompletedColor
    } else {
        TodoIncompleteColor
    }
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 3.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        ),
        color = backgroundColor,
        enabled = !todo.isCompleted
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(todo.isCompleted, onCheckedChange = onCompletedChange)
            Spacer(Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = todo.title, style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (todo.isCompleted) {TextDecoration.LineThrough} else {TextDecoration.None})

                Spacer(Modifier.height(4.dp))

                todo.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(Modifier.width(8.dp))

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }

    }

}

@Preview
@Composable

private fun TodoItemPreview() {
    MyStudyTheme {
        TodoItem(todo = todo2,
            onCompletedChange = {},
            onItemClick = {},
            onDeleteClick = {})
    }
}
