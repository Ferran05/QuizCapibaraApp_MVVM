package com.amazingapps.appquizmvvm.feature_quiz.data.datasource

import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionsCRUD {
    @GET("/questions/{num}")
    suspend fun getQuestions(@Path("num") num: Int): List<Question>
}