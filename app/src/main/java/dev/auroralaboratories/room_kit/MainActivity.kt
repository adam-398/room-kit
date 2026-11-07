package dev.auroralaboratories.room_kit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.auroralaboratories.room_kit.example.NotesScreen
import dev.auroralaboratories.room_kit.ui.theme.RoomkitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomkitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
