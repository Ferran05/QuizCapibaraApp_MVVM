package com.amazingapps.appquizmvvm.feature_quiz.presentation.mainpage

sealed class MainEvent {
    object ChangeSettingsV: MainEvent()
    data class ChangeNquestion(val num: Int): MainEvent()
}