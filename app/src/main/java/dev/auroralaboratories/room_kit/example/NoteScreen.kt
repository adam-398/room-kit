package dev.auroralaboratories.room_kit.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Shows the list of notes with a field to add new ones.
 * @param modifier Modifier applied to the root layout.
 * @param viewModel The ViewModel providing the notes.
 */
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel()
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    var title by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("New note") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (title.isNotBlank()) {
                    viewModel.addNote(title.trim())
                    title = ""
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Add")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(notes, key = { it.id }) { note ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(note.title)
                    TextButton(onClick = { viewModel.deleteNote(note) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}