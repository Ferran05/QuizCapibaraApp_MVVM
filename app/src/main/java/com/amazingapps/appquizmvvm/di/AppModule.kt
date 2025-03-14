package com.amazingapps.appquizmvvm.di

import com.amazingapps.appquizmvvm.feature_quiz.data.datasource.QuestionsCRUD
import com.amazingapps.appquizmvvm.feature_quiz.data.repository.FakeQuestionsRepo
import com.amazingapps.appquizmvvm.feature_quiz.data.repository.QuestionsRepoImpl
import com.amazingapps.appquizmvvm.feature_quiz.domain.model.Question
import com.amazingapps.appquizmvvm.feature_quiz.domain.repository.QuestionsRepo
import com.amazingapps.appquizmvvm.feature_quiz.domain.useCases.GetQuestions
import com.amazingapps.appquizmvvm.feature_quiz.domain.useCases.QuestionsUseCases
import com.amazingapps.appquizmvvm.feature_quiz.presentation.mainpage.MainViewModel
import com.amazingapps.appquizmvvm.feature_quiz.presentation.moreInfo.MoreInfoViewModel
import com.amazingapps.appquizmvvm.feature_quiz.presentation.play.PlayScreen
import com.amazingapps.appquizmvvm.feature_quiz.presentation.play.PlayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.158.211:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(QuestionsCRUD::class.java)
    }
    single<QuestionsRepo> {
        QuestionsRepoImpl(get())
    }

    single {
        QuestionsUseCases(
            getQuestions = GetQuestions(get())
        )
    }

    viewModel{ MainViewModel(get()) }
    viewModel{ MoreInfoViewModel(get()) }
    viewModel{ PlayViewModel(get()) }
}
