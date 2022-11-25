package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Update
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class Start : Applier {
    override fun apply(bot: Bot, update: Update): ExecutionOutput? {
        if (update.message!!.text.equals("/start")){
            bot.sendMessage(ChatId.fromId(update.message!!.chat.id), "Hi")

            return ExecutionOutput(State.START, "")
        }

        return null
    }
}