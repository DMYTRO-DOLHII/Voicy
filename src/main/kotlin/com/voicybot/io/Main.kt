package com.voicybot.io

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.extensions.filters.Filter
import java.util.Objects

fun main() {
    val bot = bot {
        token = "5724558538:AAFvGQy2lh6s6uiQ7NxVl-u_DChyoID15Jk"
        dispatch {
            message(Filter.Text or Filter.Command){
                println(message.text.toString())
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = message.text.toString())
            }

        }
    }
    bot.startPolling()
}