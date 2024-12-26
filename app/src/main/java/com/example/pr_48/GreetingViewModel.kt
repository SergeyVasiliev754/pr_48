package com.example.pr_48

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GreetingViewModel : ViewModel() {

    private val _greetingText = MutableStateFlow("В проекте был использован паттерн MVVM")
    val greetingText: StateFlow<String> = _greetingText

    fun updateGreeting(newGreeting: String) {
        _greetingText.value = newGreeting
    }
}
