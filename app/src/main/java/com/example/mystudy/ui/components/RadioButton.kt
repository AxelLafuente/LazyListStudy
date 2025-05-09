package com.example.mystudy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mystudy.ui.theme.Jost
import com.example.mystudy.ui.theme.MyStudyTheme
import com.example.mystudy.ui.theme.TextBlack
import com.example.mystudy.ui.theme.White

@Composable
fun RadioButtonSingleSelection(
    modifier: Modifier = Modifier,
    options: List<String> = listOf("Diaria", "Unica"),
    selectedOption: String = "Diaria",
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier
            .selectableGroup()
            .fillMaxWidth()
    ) {
        options.forEach { text ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = text,
                    color = TextBlack,
                    fontFamily = Jost,
                )
            }
        }
    }
}

@Preview
@Composable
private fun RadioButtonSingleSelectionPreview() {
    MyStudyTheme {
        RadioButtonSingleSelection(selectedOption = "Diaria", onOptionSelected = {})
    }
}
