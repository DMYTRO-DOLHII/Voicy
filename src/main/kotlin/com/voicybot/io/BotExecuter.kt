package com.voicybot.io

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.voicybot.io.bot.User
import com.voicybot.io.bot.VoicyBot
import com.voicybot.io.statemachine.StateMachine
import java.io.FileInputStream
import java.lang.Compiler.command
import java.util.Properties

fun main(){
    val token = "5724558538:AAFvGQy2lh6s6uiQ7NxVl-u_DChyoID15Jk"
    val voicy = VoicyBot(token).createBot()
    voicy.startPolling()
}