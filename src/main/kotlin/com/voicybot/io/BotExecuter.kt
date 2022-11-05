package com.voicybot.io

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.voicybot.io.bot.VoicyBot
import java.io.FileInputStream

fun main(){
//    val token = "5724558538:AAFvGQy2lh6s6uiQ7NxVl-u_DChyoID15Jk"
//    val voicy = VoicyBot(token).createBot()
//    voicy.startPolling()

    val refreshToken = FileInputStream("voicy-3e640-firebase-adminsdk-ulr28-fd0af7d6ef.json")

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(refreshToken))
        .build()

    val app = FirebaseApp.initializeApp(options)
}