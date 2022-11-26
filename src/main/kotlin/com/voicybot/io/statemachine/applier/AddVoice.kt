package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class AddVoice: Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if(input.update().message!!.text.toString() == "/addvoice"){
            bot.sendMessage(ChatId.fromId(input.id()), "Send me a voice/audio or link on this voice(YouTube or TikTok)")

            return ExecutionOutput(State.ADD_VOICE, "")
        }

        return null
    }
}