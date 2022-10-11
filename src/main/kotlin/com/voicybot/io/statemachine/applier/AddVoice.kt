package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.state.State

class AddVoice: Applier {
    override fun apply(bot: Bot, message: Message): State? {
        if(message.text.toString() == "/addvoice"){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Send me a voice/audio")

            return State.ADD_VOICE
        }

        return null
    }
}