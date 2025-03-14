package com.amazingapps.appquizmvvm.feature_quiz.data.repository

import com.amazingapps.appquizmvvm.feature_quiz.data.datasource.QuestionsCRUD
import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question
import com.amazingapps.appquizmvvm.feature_quiz.domain.repository.QuestionsRepo

class QuestionsRepoImpl(
    private val crud: QuestionsCRUD
): QuestionsRepo {
    override suspend fun getQuestions(num: Int): List<Question> {
        return crud.getQuestions(num)
    }
}