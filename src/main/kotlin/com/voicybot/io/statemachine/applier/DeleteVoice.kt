package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Update
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class DeleteVoice : Applier {
    override fun apply(bot: Bot, update: Update): ExecutionOutput? {
        if(update.message!!.text.toString() == "/deletevoice"){
            bot.sendMessage(ChatId.fromId(update.message!!.chat.id), "Select the button with voice name," +
                    "that you would like to delete")


            return ExecutionOutput(State.DELETE_VOICE, "${update.message!!.from!!.id}")
        }

        return null
    }
}