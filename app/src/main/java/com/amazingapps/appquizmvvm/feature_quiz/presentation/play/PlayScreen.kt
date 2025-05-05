package com.amazingapps.appquizmvvm.feature_quiz.presentation.play

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusTargetModifierNode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.amazingapps.appquizmvvm.R
import com.amazingapps.appquizmvvm.feature_quiz.presentation.play.composables.CorrectCard
import com.amazingapps.appquizmvvm.feature_quiz.presentation.play.composables.FalseCard
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.NumQuestionState
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.Screen
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.SoundManager
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.composables.ButtonMain
import org.koin.androidx.compose.getViewModel

@Composable
fun PlayScreen(navController: NavController, viewModel: PlayViewModel = getViewModel()) {
    val state = viewModel.state.value
    val currentQuestion = viewModel.currentQuestion.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val soundManager = remember { SoundManager(context) }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            soundManager.reproduir("musica")
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                soundManager.pausar()
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                soundManager.alliberar()
            }
        }
        lifecycleOwner.addObserver(observer)
        onDispose {
            lifecycleOwner.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        soundManager.reproduir("musica")
    }



    if (currentQuestion.value == null) {
        when (state.start) {
            true -> {
                Column( modifier = Modifier.fillMaxSize().background(color = Color(0xFFFF903A)).padding(top = 30.dp),  horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier.fillMaxWidth().padding(start = 8.dp), horizontalArrangement = Arrangement.Start) {
                        IconButton(
                            onClick = {
                                val sharedPreferences = context.getSharedPreferences("clip_prefs", Context.MODE_PRIVATE)
                                val volume = sharedPreferences.getFloat("volume_level", 1.0f)
                                // Create and configure MediaPlayer
                                val mediaPlayer = MediaPlayer.create(context, R.raw.button)
                                mediaPlayer.setVolume(volume, volume)

                                mediaPlayer.setOnCompletionListener {
                                    it.release()
                                }

                                mediaPlayer.start()



                                navController.navigate(Screen.MainScreen.route)},
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.White, shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                tint = Color(0xffe86620),
                                contentDescription = "Tornar",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    Text("Inici", fontSize = 30.sp, fontFamily = FontFamily.Serif, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 15.dp))
                    Card(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)) {

                            Image(
                                painter = painterResource(R.drawable.capisherlock),
                                contentDescription = "Sherlock",
                                modifier = Modifier.fillMaxWidth().height(250.dp)
                            )
                            Column (horizontalAlignment = Alignment.Start, modifier = Modifier.padding(horizontal = 10.dp)) {
                                Text("Com Funciona?", fontSize = 19.sp, fontFamily = FontFamily.Serif)
                                Text("- Es mostraràn una serie de preguntes", modifier = Modifier.padding(start = 16.dp))
                                Text("- Pots canviar el nombre de preguntes a configuració", modifier = Modifier.padding(start = 16.dp))
                                HorizontalDivider(
                                    modifier = Modifier.padding(top = 3.dp, bottom = 8.dp),
                                    thickness = 1.dp,
                                    color = Color(0xFFFF903A)
                                )
                                Text("Contesta totes les preguntes correctament per ser un expert en: ")
                                Row (horizontalArrangement = Arrangement.Absolute.Center,  modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp, top = 8.dp)) {
                                    Text("Capibaras", fontSize = 19.sp, fontFamily = FontFamily.Serif)
                                    Text(" !!")
                                }
                            }
                            ButtonMain(text = "Començar", onclick = { viewModel.nextQuestion() })
                        }
                    }
                }
            }

            false -> {
                Column(modifier = Modifier.fillMaxSize().background(color = Color(0xFFFF903A)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            if (state.correct == NumQuestionState.npreguntes / 2) {
                                Image(
                                    painter = painterResource(R.drawable.capifitifiti),
                                    contentDescription = "Fititi",
                                    modifier = Modifier.fillMaxWidth().height(300.dp)
                                )
                            } else if (state.correct == NumQuestionState.npreguntes) {
                                Image(
                                    painter = painterResource(R.drawable.capinerd),
                                    contentDescription = "Nerd",
                                    modifier = Modifier.fillMaxWidth().height(300.dp)
                                )
                            } else if (state.correct > NumQuestionState.npreguntes / 2) {
                                Image(
                                    painter = painterResource(R.drawable.capiswim),
                                    contentDescription = "happy",
                                    modifier = Modifier.fillMaxWidth().height(300.dp)
                                )
                            } else if (state.correct < NumQuestionState.npreguntes && state.correct != 0) {
                                Image(
                                    painter = painterResource(R.drawable.capiangry),
                                    contentDescription = "angry",
                                    modifier = Modifier.fillMaxWidth().height(300.dp)
                                )
                            } else {
                                Image(
                                    painter = painterResource(R.drawable.capiscared),
                                    contentDescription = "scared",
                                    modifier = Modifier.fillMaxWidth().height(300.dp)
                                )
                            }
                            Text("La teva Puntuació:", fontSize = 19.sp)
                            Text(state.correct.toString(), fontSize = 35.sp, fontFamily = FontFamily.Serif)
                            ButtonMain(
                                text = "Tornar",
                                onclick = {
                                    navController.navigate(Screen.MainScreen.route)
                                    viewModel.onEvent(PlayEvent.ResetPoints)
                                }
                            )
                        }
                    }
                }
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxSize().background(color = Color(0xFFFF903A)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(currentQuestion.value?.pregunta.toString(), fontFamily = FontFamily.Serif, fontSize = 21.sp, color = Color.White, modifier = Modifier.padding(bottom = 10.dp), textAlign = TextAlign.Center)
            Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                Column (Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    currentQuestion.value?.respostes?.forEach { resposta ->
                        resposta.text?.let {
                            ButtonMain(
                                text = it,
                                onclick = {
                                    if (resposta.isCorrect == true) {
                                        viewModel.onEvent(PlayEvent.CorrectAnswer)
                                        viewModel.onEvent(PlayEvent.CardCorrectV)

                                    } else {
                                        viewModel.onEvent(PlayEvent.CardFalseV)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
        currentQuestion.value?.respostes?.find { it.isCorrect == true }?.text?.let {
            FalseCard(
                isVisble = state.isFalseVisible,
                buttonOnClick = {viewModel.onEvent(PlayEvent.CardFalseV)},
                buttonOnClick2 = {viewModel.onEvent(PlayEvent.NextQuestio)},
                correct = it
            )
        }
        CorrectCard(
            isVisble = state.isCorrectVisible,
            buttonOnClick = {viewModel.onEvent(PlayEvent.CardCorrectV)},
            buttonOnClick2 = {viewModel.onEvent(PlayEvent.NextQuestio)}
        )
    }
}