package com.example.pr_48

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr_48.ui.theme.Pr_48Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pr_48Theme {
                GreetingScreen()
            }
        }
    }
}

@Composable
fun GreetingScreen(viewModel: GreetingViewModel = viewModel()) {
    val greeting by viewModel.greetingText.collectAsState()
    val context = LocalContext.current // Получаем контекст

    // Проверяем, был ли передан новый greeting через Intent
    val intent = (context as? MainActivity)?.intent
    val newGreeting = intent?.getStringExtra("newGreeting")

    if (newGreeting != null) {
        viewModel.updateGreeting(newGreeting) // Обновляем greeting в ViewModel
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Text("Текущее приветствие: $greeting")
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Переход на второй экран для изменения приветствия
                val intent = Intent(context, SecondScreenActivity::class.java)
                context.startActivity(intent) // Используем контекст для запуска активности
            }) {
                Text("Перейти ко второму экрану")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingScreenPreview() {
    Pr_48Theme {
        GreetingScreen(viewModel = GreetingViewModel())
    }
}
