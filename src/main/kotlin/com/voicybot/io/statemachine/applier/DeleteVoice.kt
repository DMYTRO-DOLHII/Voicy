package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class DeleteVoice : Applier {
    override fun apply(bot: Bot, message: Message): ExecutionOutput? {
        if(message.text.toString() == "/deletevoice"){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Use @VoicySticker to choose voice that you " +
                    "would like to delete")

            return ExecutionOutput(State.DELETE_VOICE, "")
        }

        return null
    }
}