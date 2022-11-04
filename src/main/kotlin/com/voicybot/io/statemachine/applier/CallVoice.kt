package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class CallVoice : Applier {

    private var names = mutableListOf<String>()

    override fun apply(bot: Bot, message: Message): ExecutionOutput? {
        if (message.text != null) {

            if (names.contains(message.text.toString())) {
                bot.sendMessage(ChatId.fromId(message.chat.id),
                    "Sorry, but you already have a voice with the same name..." +
                            " Try another name")
                return ExecutionOutput(State.GET_VOICE, "")
            }
            bot.sendMessage(ChatId.fromId(message.chat.id), "Now your sticker has name : ${message.text.toString()}")
            names.add(message.text.toString())
            return ExecutionOutput(State.CALL_VOICE, message.text.toString())
        }

        return null
    }
}