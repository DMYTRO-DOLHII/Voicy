package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Update
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class BackToMain : Applier {
    override fun apply(bot: Bot, update: Update): ExecutionOutput? {
        if (update.message!!.text == "/backtomain"){
            bot.sendMessage(ChatId.fromId(update.message!!.chat.id), "Hello there!\nYou have returned to start position, " +
                    "use commands to do something via me!")

            return ExecutionOutput(State.BACK_TO_MAIN, "")
        }

        return null
    }
}