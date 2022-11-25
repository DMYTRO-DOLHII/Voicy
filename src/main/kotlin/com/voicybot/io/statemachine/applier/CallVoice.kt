package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Update
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class CallVoice : Applier {

    private var names = mutableListOf<String>()

    override fun apply(bot: Bot, update: Update): ExecutionOutput? {
        if (update.message!!.text != null) {

            if (names.contains(update.message!!.text.toString())) {
                bot.sendMessage(ChatId.fromId(update.message!!.chat.id),
                    "Sorry, but you already have a voice with the same name..." +
                            " Try another name")
                return ExecutionOutput(State.GET_VOICE, "")
            }
            bot.sendMessage(ChatId.fromId(update.message!!.chat.id), "Now your sticker has name : ${update.message!!.text.toString()}")
            names.add(update.message!!.text.toString())
            return ExecutionOutput(State.CALL_VOICE, update.message!!.text.toString())
        }

        return null
    }
}