package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class DeleteGetVoice : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().callbackQuery != null) {
            bot.sendMessage(ChatId.fromId(input.id()), "Your sticker ${input.update().message!!.text} has been deleted!")
            return ExecutionOutput(State.DELETE_GET_VOICE, input.update().callbackQuery!!.data)
        }

        return null
    }
}