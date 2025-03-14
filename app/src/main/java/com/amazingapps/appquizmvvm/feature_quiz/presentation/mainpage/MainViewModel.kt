package com.amazingapps.appquizmvvm.feature_quiz.presentation.mainpage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.amazingapps.appquizmvvm.feature_quiz.domain.useCases.QuestionsUseCases
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.NumQuestionState

class MainViewModel(
    private val useCases: QuestionsUseCases
): ViewModel(){

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    fun onEvent(event: MainEvent) {
        when(event){
            MainEvent.ChangeSettingsV -> {
                changeVisibility()
            }

            is MainEvent.ChangeNquestion -> {
                changeNumQ(event.num)
            }
        }
    }

    private fun changeNumQ(num: Int) {
        NumQuestionState.updateValue(num)
    }


    private fun changeVisibility() {
        _state.value = state.value.copy(
            isSettingsVisible = !_state.value.isSettingsVisible
        )
    }
       
}