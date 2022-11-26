package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class BackToMain : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().message!!.text == "/backtomain"){
            bot.sendMessage(ChatId.fromId(input.id()), "Hello there!\nYou have returned to start position, " +
                    "use commands to do something via me!")

            return ExecutionOutput(State.BACK_TO_MAIN, "")
        }

        return null
    }
}