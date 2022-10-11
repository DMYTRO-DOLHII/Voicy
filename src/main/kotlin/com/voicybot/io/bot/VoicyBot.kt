package com.voicybot.io.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.logging.LogLevel
import com.voicybot.io.storage.UserStorage
import okhttp3.Dispatcher

class VoicyBot(private var TOKEN: String) {

    private var users: UserStorage = UserStorage()

    public fun createBot(): Bot{
        return bot {
            token = TOKEN
            logLevel = LogLevel.Network.Body

            dispatch {
                message(Filter.Text or Filter.Command){

                }
            }
        }
    }

}