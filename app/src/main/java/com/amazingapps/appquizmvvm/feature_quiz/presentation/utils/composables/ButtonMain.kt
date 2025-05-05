package com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.composables

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amazingapps.appquizmvvm.R
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.SoundManager

@Composable
fun ButtonMain(
    text: String,
    onclick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Button(
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

            onclick()},
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe86620), contentColor = Color(0xff4d2502)),
        modifier = Modifier.width(300.dp).padding(bottom = 1.dp)


        ){
        Text(text=text, fontSize = 18.sp)
    }
}