package com.amazingapps.appquizmvvm.feature_quiz.presentation.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object NumQuestionState {
    var npreguntes: Int by mutableIntStateOf(10)

    fun updateValue(newValue: Int){
        npreguntes = newValue
    }
}