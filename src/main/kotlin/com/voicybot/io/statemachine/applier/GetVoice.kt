package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class GetVoice : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().message!!.audio != null) {
            bot.sendMessage(
                ChatId.fromId(input.id()),
                "Great! Now, How would you like to name your sticker?"
            )
            return ExecutionOutput(State.GET_VOICE, input.update().message!!.audio!!.fileId)
        }

        if (input.update().message!!.voice != null) {
            bot.sendMessage(
                ChatId.fromId(input.id()),
                "Great! Now, How would you like to name your sticker?"
            )
            return ExecutionOutput(State.GET_VOICE, input.update().message!!.voice!!.fileId)
        }

        return null
    }
}