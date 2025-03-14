package com.amazingapps.appquizmvvm.feature_quiz.domain.model

data class Question (
    val id: Int?,
    val pregunta: String?,
    val respostes: List<Answer>
)