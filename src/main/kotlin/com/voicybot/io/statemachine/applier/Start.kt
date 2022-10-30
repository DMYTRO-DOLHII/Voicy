package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class Start : Applier {
    override fun apply(bot: Bot, message: Message): ExecutionOutput? {
        if (message.text.equals("/start")){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Hi")

            return ExecutionOutput(State.START, "")
        }

        return null
    }
}