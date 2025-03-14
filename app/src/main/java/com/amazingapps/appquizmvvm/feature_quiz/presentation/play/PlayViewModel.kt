package com.amazingapps.appquizmvvm.feature_quiz.presentation.play

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question
import com.amazingapps.appquizmvvm.feature_quiz.domain.useCases.QuestionsUseCases
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.NumQuestionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayViewModel(
    private val useCases: QuestionsUseCases
): ViewModel() {

    private val _state = mutableStateOf(PlayState())
    val state: State<PlayState> = _state


    private var _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex

    private val _currentQuestion = MutableStateFlow(_state.value.questions.firstOrNull())
    val currentQuestion: StateFlow<Question?> = _currentQuestion

    init {
        getQuestions()
    }
    fun onEvent(event: PlayEvent){
        when(event){
            is PlayEvent.NextQuestio -> {
                nextQuestion()
            }
            is PlayEvent.CardCorrectV -> {
                _state.value = state.value.copy(
                    isCorrectVisible = !_state.value.isCorrectVisible
                )
            }
            is PlayEvent.CardFalseV -> {
                _state.value = state.value.copy(
                    isFalseVisible = !_state.value.isFalseVisible
                )
            }

            is PlayEvent.CorrectAnswer -> {
                _state.value = state.value.copy(
                    correct =  state.value.correct + 1
                )
            }

            is PlayEvent.ResetPoints -> {
                _state.value = state.value.copy(
                    correct =  0
                )
            }
        }
    }
    private fun getQuestions(){
        viewModelScope.launch {
            var result = useCases.getQuestions(NumQuestionState.npreguntes)
            Log.w("A", NumQuestionState.npreguntes.toString())
            _state.value = state.value.copy(
                questions = result
            )
        }
    }

    fun nextQuestion(){
        if (_state.value.start){
            _state.value = _state.value.copy(
                start = false
            )
            _currentIndex.value = 0
            _currentQuestion.value = _state.value.questions.firstOrNull()
        }
        else if (_currentIndex.value < _state.value.questions.size - 1){
            _currentIndex.value++
            _currentQuestion.value = _state.value.questions[_currentIndex.value]
        } else {
            _currentQuestion.value = null
        }
    }

}