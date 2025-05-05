package com.amazingapps.appquizmvvm.feature_quiz.presentation.moreInfo
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amazingapps.appquizmvvm.R
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.Screen
import com.amazingapps.appquizmvvm.feature_quiz.presentation.utils.SoundManager
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreInfoScreen( navController: NavController, viewModel: MoreInfoViewModel = getViewModel()) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.background(Color(0xFFFF903A)).fillMaxSize().padding(8.dp)) {

        item {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column {
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
                    Text(
                        "Informació",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 75.dp)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.capisee),
                    contentDescription = "capi ojeando"
                )
            }

        }
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Descobreix el món dels capibares amb aquesta increïble aplicació. Aprèn dades curioses, observa imatges i vídeos d’aquests adorables animals i gaudeix d’una experiència única.")
            }
        }
        item {
            Text(
                "A qui està dirigit",
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
        item {
            Text("Aquesta aplicació està dirigida a:")
            Text("Amants dels animals i la natura.")
            Text("Nens i adults interessats a aprendre sobre la fauna.")
            Text("Persones que busquen contingut relaxant i educatiu.")
        }
        item {
            Text(
                "Curiositats: ",
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )

        }
        item {
            LazyRow {
                item {
                    Card(modifier = Modifier.fillParentMaxSize().padding(8.dp)) {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.capisteroids),
                                contentDescription = "Capi on steroids"
                            )
                            Text(" Els capibares són els rosegadors més grans del món.", modifier = Modifier.padding(top = 10.dp, end = 8.dp))
                        }
                    }
                }
                item {
                    Card(modifier = Modifier.fillParentMaxSize().padding(8.dp)) {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.capiswim),
                                contentDescription = "Capi swimming"
                            )
                            Text("Són excel·lents nedadors i poden romandre sota l’aigua fins a 5 minuts.", modifier = Modifier.padding(top = 10.dp, end = 8.dp))
                        }
                    }
                }
                item {
                    Card(modifier = Modifier.fillParentMaxSize().padding(8.dp)) {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.capinteract),
                                contentDescription = "Capi and a frog"
                            )
                            Text("Tenen una personalitat social i sovint es veuen interactuant amb altres animals.", modifier = Modifier.padding(top = 10.dp, end = 8.dp))
                        }
                    }
                }
                item {
                    Card(modifier = Modifier.fillParentMaxSize().padding(8.dp)) {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.capisleepy),
                                contentDescription = "Sleepy Capi"
                            )
                            Text("Els capibares dormen molt poc, ja que han d’estar alerta als depredadors.", modifier = Modifier.padding(top = 10.dp, end = 8.dp))
                        }
                    }
                }
                item {
                    Card(modifier = Modifier.fillParentMaxSize().padding(8.dp)) {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.capihappy),
                                contentDescription = "Capi Happy"
                            )
                            Text("Poden comunicar-se amb una varietat de sons, com grunyits i xiulets.", modifier = Modifier.padding(top = 10.dp, end = 8.dp))
                        }
                    }
                }
            }
        }
    }
}