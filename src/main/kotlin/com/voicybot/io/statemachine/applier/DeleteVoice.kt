package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class DeleteVoice : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        // TODO : bot crushes when trying to delete sticker
        if(input.update().message!!.text.toString() == "/deletevoice"){
            bot.sendMessage(ChatId.fromId(input.id()), "Select the button with voice name," +
                    "that you would like to delete")


            return ExecutionOutput(State.DELETE_VOICE, "${input.id()}")
        }

        return null
    }
}