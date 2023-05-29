package com.voicybot.io

import com.voicybot.io.bot.VoicyBot

fun main(){
    val token = "5724558538:AAFvGQy2lh6s6uiQ7NxVl-u_DChyoID15Jk"
    val voicy = VoicyBot(token).createBot()
    voicy.startPolling()
}