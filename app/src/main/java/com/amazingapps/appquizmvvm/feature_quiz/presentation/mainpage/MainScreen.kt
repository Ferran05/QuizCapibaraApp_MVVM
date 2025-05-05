package com.amazingapps.appquizmvvm.feature_quiz.presentation.mainpage

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amazingapps.appquizmvvm.R
import com.amazingapps.appquizmvvm.feature_quiz.presentation.mainpage.composables.CardSettings
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.NumQuestionState
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.composables.ButtonMain
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.Screen
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.SoundManager
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen( navController: NavController, viewModel: MainViewModel = getViewModel()){
    val state = viewModel.state.value
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()
                              .background(color = Color(0xFFFF903A)).pointerInput(Unit) {
            detectVerticalDragGestures { _, dragAmount ->
                if (dragAmount < -50) {
                    navController.navigate(Screen.MoreInfoScreen.route)
                }
                if (dragAmount < 50) {
                    viewModel.onEvent(MainEvent.ChangeSettingsV)
                }
            }

            },
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth().padding(top = 50.dp, start = 8.dp, bottom =  15.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("CapibaraLAND", fontSize = 30.sp, fontFamily = FontFamily.Serif, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))
            IconButton(
                onClick = {
                    val sharedPreferences = context.getSharedPreferences("clip_prefs", Context.MODE_PRIVATE)
                    if (SoundManager(context).isSorollActiu()) {
                        val volume = sharedPreferences.getFloat("volume_level", 1.0f)
                        val mediaPlayer = MediaPlayer.create(context, R.raw.button)
                        mediaPlayer.setVolume(volume, volume)

                        mediaPlayer.setOnCompletionListener {
                            it.release()
                        }

                        mediaPlayer.start()
                    }

                    viewModel.onEvent(MainEvent.ChangeSettingsV)},
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    tint = Color(0xffe86620),
                    contentDescription = "Settings",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Card( modifier = Modifier.padding(8.dp),
            //colors = CardDefaults.cardColors(containerColor = Color(0xFFFF903A))
            ) {
            Image(
                painter = painterResource(id = R.drawable.capinici),
                contentDescription = "Foto capi Inici",
                modifier = Modifier.fillMaxWidth().height(400.dp)
            )
            Column(modifier = Modifier.padding(bottom = 30.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                ButtonMain(
                    "Més Informació",
                    onclick = {
                        navController.navigate(Screen.MoreInfoScreen.route)
                    }
                )
                ButtonMain(
                    "Jugar",
                    onclick = {
                        navController.navigate(Screen.PlayScreen.route)
                    }
                )
            }
        }

    }
    CardSettings(
        isVisble = state.isSettingsVisible,
        buttonOnClick = {viewModel.onEvent(MainEvent.ChangeSettingsV)},
        sliderAction = {value -> viewModel.onEvent(MainEvent.ChangeNquestion(value))},
        numPreguntes = NumQuestionState.npreguntes.toFloat()
    )
}

fun hello() {
    println("holA")
}
