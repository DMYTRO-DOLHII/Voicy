package com.voicybot.io.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
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
                command("start"){
                    if(users.get(message.chat.id) == null){
                        users.add(
                            message.chat.id,
                            User(message.chat.id,
                                message.chat.username.toString(),
                                message.chat.firstName.toString(),
                                message.chat.lastName.toString()))
                    }

                    bot.sendMessage(ChatId.fromId(message.chat.id), "")
                }

                message(Filter.Text or Filter.Command){
                    users.get(message.chat.id)!!.run(message, bot)
                }
            }
        }
    }

}