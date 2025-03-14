package com.amazingapps.appquizmvvm.feature_quiz.data.repository

import com.amazingapps.appquizmvvm.feature_quiz.data.datasource.QuestionsCRUD
import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Answer
import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question
import com.amazingapps.appquizmvvm.feature_quiz.domain.repository.QuestionsRepo
import kotlinx.coroutines.delay

class FakeQuestionsRepo(
    private val crud: QuestionsCRUD
) : QuestionsRepo {

    private val questions = listOf(
        Question(
            id = 1,
            pregunta = "¿Cuál es la capital de Francia?",
            respostes = listOf(
                Answer("Madrid", false),
                Answer("Berlín", false),
                Answer("París", true),
                Answer("Roma", false)
            )
        ),
        Question(
            id = 2,
            pregunta = "¿Cuánto es 2 + 2?",
            respostes = listOf(
                Answer("3", false),
                Answer("4", true),
                Answer("5", false),
                Answer("6", false)
            )
        ),
        Question(
            id = 3,
            pregunta = "¿Quién escribió 'Cien años de soledad'?",
            respostes = listOf(
                Answer("Gabriel García Márquez", true),
                Answer("Mario Vargas Llosa", false),
                Answer("Pablo Neruda", false),
                Answer("Julio Cortázar", false)
            )
        )
    )

    override suspend fun getQuestions(num: Int): List<Question> {
        delay(500) // Simula un tiempo de carga
        return questions.take(num)
    }
}
