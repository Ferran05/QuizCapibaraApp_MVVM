package com.amazingapps.appquizmvvm.feature_quiz.domain.useCases

import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question
import com.amazingapps.appquizmvvm.feature_quiz.domain.repository.QuestionsRepo

class GetQuestions(
    private val repo: QuestionsRepo
) {
    suspend operator fun invoke(num: Int): List<Question> {
        return repo.getQuestions(num)
    }
}