package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class DeleteGetVoice : Applier {
    override fun apply(bot: Bot, message: Message): ExecutionOutput? {
        if (message.audio != null) {
            bot.sendMessage(ChatId.fromId(message.chat.id), "Your sticker has been deleted!")
            return ExecutionOutput(State.GET_VOICE, message.audio!!.fileId)
        }

        if (message.voice != null) {
            bot.sendMessage(ChatId.fromId(message.chat.id), "Your sticker has been deleted!")
            return ExecutionOutput(State.GET_VOICE, message.voice!!.fileId)
        }

        return null;
    }
}