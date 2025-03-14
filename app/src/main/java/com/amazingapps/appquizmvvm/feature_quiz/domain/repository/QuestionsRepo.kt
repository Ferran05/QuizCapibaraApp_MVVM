package com.amazingapps.appquizmvvm.feature_quiz.domain.repository

import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question

interface QuestionsRepo {
    suspend fun getQuestions(num: Int): List<Question>
}