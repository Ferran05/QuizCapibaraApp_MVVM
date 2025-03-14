package com.amazingapps.appquizmvvm.feature_quiz.presentation.play

import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question

data class PlayState(
    val questions: List<Question> = emptyList(),
    val isCorrect: Boolean? = null,
    val correct: Int = 0,
    val start: Boolean = true,
    val isCorrectVisible: Boolean = false,
    val isFalseVisible: Boolean = false
)
