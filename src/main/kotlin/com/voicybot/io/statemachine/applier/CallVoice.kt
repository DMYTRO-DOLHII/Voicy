package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class CallVoice : Applier {

    private var names = mutableListOf<String>()

    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().message!!.text != null) {
            if (names.contains(input.update().message!!.text.toString())) {
                bot.sendMessage(ChatId.fromId(input.id()),
                    "Sorry, but you already have a voice with the same name..." +
                            " Try another name")
                return ExecutionOutput(State.GET_VOICE, "")
            }
            bot.sendMessage(ChatId.fromId(input.id()), "Now your sticker has name : ${input.update().message!!.text.toString()}")
            names.add(input.update().message!!.text.toString())
            return ExecutionOutput(State.CALL_VOICE, input.update().message!!.text.toString())
        }

        return null
    }
}