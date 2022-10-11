package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.state.State

class CallVoice: Applier {
    override fun apply(bot: Bot, message: Message): State? {
        if(message.text != null){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Now your sticker has name : ${message.text.toString()}")
            return State.CALL_VOICE
        }

        return null
    }
}