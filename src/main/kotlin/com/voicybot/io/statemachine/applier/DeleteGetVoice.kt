package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class DeleteGetVoice : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().callbackQuery != null) {
            bot.sendMessage(ChatId.fromId(input.id()), "Your sticker ${input.update().callbackQuery!!.data} has been deleted!")
            input.user().getVoices().deleteVoice()
            bot.sendMessage(ChatId.fromId(input.id()), "Now you have used ${input.user().getVoices().getUsed()} of " +
                    "${input.user().getVoices().getMaximum()} stickers")
            return ExecutionOutput(State.DELETE_GET_VOICE, input.update().callbackQuery!!.data)
        }

        return null
    }
}