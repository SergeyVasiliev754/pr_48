package com.example.pr_48

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr_48.ui.theme.Pr_48Theme

class SecondScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pr_48Theme {
                SecondScreen()
            }
        }
    }
}

@Composable
fun SecondScreen(viewModel: GreetingViewModel = viewModel()) {
    var newGreeting by remember { mutableStateOf("") } // Для хранения нового приветствия
    val context = LocalContext.current // Получаем контекст

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Text("Введите новое приветствие:")
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = newGreeting,
                onValueChange = { newGreeting = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Введите новое приветствие...") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Отправляем новое приветствие на главную активность
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("newGreeting", newGreeting) // Передаем новое приветствие
                context.startActivity(intent) // Возвращаемся на главную активность
            }) {
                Text("Обновить приветствие и вернуться на главный экран")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    Pr_48Theme {
        SecondScreen()
    }
}
