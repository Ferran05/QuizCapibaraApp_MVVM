package com.amazingapps.appquizmvvm.feature_quiz.presentation.mainpage.composables

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.composables.ButtonMain
import androidx.core.content.edit

@Composable
fun CardSettings(
    buttonOnClick: () -> Unit,
    isVisble: Boolean,
    modifier: Modifier = Modifier,
    numPreguntes: Float,
    sliderAction: (Int) -> Unit
) {

    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("clip_prefs", Context.MODE_PRIVATE) }
    var volumeState by remember { mutableStateOf(prefs.getFloat("volume_level", 0.5f)) }

    AnimatedVisibility(
        visible = isVisble,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
            .background(Color(0xFFFF903A).copy(alpha = 0.8f)),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(top= 100.dp)) {
                Text("Configuració", fontSize = 30.sp, fontFamily = FontFamily.Serif, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 20.dp, start = 8.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Card(modifier.padding(8.dp)) {
                        Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Row (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                                Text("Num. de preguntes: ", fontFamily = FontFamily.Serif, fontSize = 18.sp)
                                Text(numPreguntes.toInt().toString(), fontFamily = FontFamily.Serif, fontSize = 18.sp)
                            }
                            Slider(
                                value = numPreguntes,
                                onValueChange = {
                                    sliderAction(it.toInt())
                                },
                                valueRange = 5f..10f,
                                steps = 8,
                                modifier = Modifier.padding(horizontal = 16.dp),
                                colors = SliderDefaults.colors(
                                    thumbColor = Color(0xFFFF903A),
                                    activeTrackColor = Color(0xffe86620),
                                    inactiveTrackColor = Color.Gray
                                )

                            )
                            Slider(
                                value = volumeState,
                                onValueChange = { newVolume ->
                                    volumeState = newVolume
                                    prefs.edit() { putFloat("volume_level", newVolume) }
                                },
                                valueRange = 0f..1f,
                                modifier = Modifier.padding(horizontal = 16.dp),
                                colors = SliderDefaults.colors(
                                    thumbColor = Color(0xFFFF903A),
                                    activeTrackColor = Color(0xffe86620),
                                    inactiveTrackColor = Color.Gray
                                )
                            )
                            ButtonMain(
                                text = "guardar",
                                onclick = {
                                    buttonOnClick()
                                }
                            )
                        }
                    }

                }
            }
        }
    }

}