package com.voicybot.io.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.logging.LogLevel
import okhttp3.Dispatcher

class VoicyBot(private var TOKEN: String) {

    public fun createBot(): Bot{
        return bot {
            token = TOKEN
            logLevel = LogLevel.Network.Body

            dispatch {
//                setUpInput()
            }
        }
    }

    private fun Dispatcher.setUpInput(){

    }
}