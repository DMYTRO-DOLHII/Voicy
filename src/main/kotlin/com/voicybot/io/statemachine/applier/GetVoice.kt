package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.state.State

class GetVoice: Applier {
    override fun apply(bot: Bot, message: Message): State? {
        if(message.audio != null || message.voice != null){
            return State.GET_VOICE
        }

        return null;
    }
}