package com.amazingapps.appquizmvvm.feature_quiz.presentation.play


sealed class PlayEvent {
    object NextQuestio: PlayEvent()
    object CardCorrectV: PlayEvent()
    object CardFalseV: PlayEvent()
    object CorrectAnswer: PlayEvent()
    object ResetPoints: PlayEvent()
}