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

            dispatch {
                command("start"){
                    if(users.get(message.chat.id) == null){
                        users.add(
                            message.chat.id,
                            User(message.chat.id,
                                message.chat.username.toString(),
                                message.chat.firstName.toString(),
                                message.chat.lastName.toString()))

                        println("User " + message.chat.id +  "was added")
                    }

                }

                message(Filter.Text or Filter.Command){
                    println("Trying to handle message or command...")
                    users.get(message.chat.id)!!.run(bot, message)
                }
            }
        }
    }

}