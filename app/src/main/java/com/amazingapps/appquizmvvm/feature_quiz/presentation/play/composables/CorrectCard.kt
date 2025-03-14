package com.amazingapps.appquizmvvm.feature_quiz.presentation.play.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amazingapps.appquizmvvm.R
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.composables.ButtonMain

@Composable
fun CorrectCard(
    buttonOnClick: () -> Unit,
    buttonOnClick2: () -> Unit,
    isVisble: Boolean,
    modifier: Modifier = Modifier
) {
        AnimatedVisibility(
            visible = isVisble,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(Color(0xFFFF903A).copy(alpha = 0.5f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(R.drawable.capiwin),
                            contentDescription = "Correct Anwser",
                            modifier = Modifier.size(300.dp)
                        )
                        Text("Resposta correcta!", fontSize = 21.sp, fontFamily = FontFamily.Serif, modifier = Modifier.padding(8.dp))
                        ButtonMain(
                            text = "continuar",
                            onclick = {
                                buttonOnClick()
                                buttonOnClick2()
                            }
                        )
                    }
                }
            }
        }

}