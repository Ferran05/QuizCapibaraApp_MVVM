package com.amazingapps.appquizmvvm.feature_quiz.presentation.utils

import android.content.Context
import android.media.MediaPlayer
import com.amazingapps.appquizmvvm.R

class SoundManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private var currentClip: String? = null
    private val prefs = context.getSharedPreferences("clip_prefs", Context.MODE_PRIVATE)

    private val soundMap = mapOf(
        "musica" to R.raw.music
    )

    fun setVolume(volume: Float) {
        mediaPlayer?.setVolume(volume, volume)
        guardarVolume(volume)
    }

    private fun guardarVolume(volume: Float) {
        prefs.edit().putFloat("volume_level", volume).apply()
    }

    private fun obtenerVolume(): Float {
        return prefs.getFloat("volume_level", 0.5f)
    }

    fun reproduir(nom: String) {
        if (nom == currentClip && mediaPlayer?.isPlaying == true) {
            return // Ya está reproduciendo
        }

        alliberar() // Libera anterior si hay

        val resId = soundMap[nom] ?: return
        val volume = obtenerVolume()
        val pos = prefs.getInt("${nom}_pos", 0)

        currentClip = nom
        mediaPlayer = MediaPlayer.create(context, resId)?.apply {
            setVolume(volume, volume)
            isLooping = true
            seekTo(pos)
            start()
        }

        guardarEstat(nom, pos, true)
    }

    fun pausar() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                guardarEstat(currentClip, it.currentPosition, false)
            }
        }
    }

    fun reprendreUltimClip() {
        // Si ya hay uno activo, no hacer nada
        if (mediaPlayer != null) return

        val nom = prefs.getString("clip_nom", null) ?: return
        val pos = prefs.getInt("${nom}_pos", 0)
        val estavaReproduint = prefs.getBoolean("clip_playing", false)
        val resId = soundMap[nom] ?: return
        val volume = obtenerVolume()

        currentClip = nom
        mediaPlayer = MediaPlayer.create(context, resId)?.apply {
            setVolume(volume, volume)
            isLooping = true
            seekTo(pos)
            if (estavaReproduint) start()
        }

        guardarEstat(nom, pos, estavaReproduint)
    }

    fun alliberar() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun guardarEstat(nom: String?, pos: Int, enReproduccio: Boolean) {
        prefs.edit().apply {
            putString("clip_nom", nom)
            if (nom != null) {
                putInt("${nom}_pos", pos)
            }
            putBoolean("clip_playing", enReproduccio)
            apply()
        }
    }
}
