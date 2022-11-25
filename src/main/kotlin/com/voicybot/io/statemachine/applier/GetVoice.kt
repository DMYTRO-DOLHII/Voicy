package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Update
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class GetVoice: Applier {
    override fun apply(bot: Bot, update: Update): ExecutionOutput? {
        if(update.message!!.audio != null){
            bot.sendMessage(ChatId.fromId(update.message!!.chat.id), "Great! Now, How would you like to name your sticker?")
            return ExecutionOutput(State.GET_VOICE, update.message!!.audio!!.fileId)
        }

        if(update.message!!.voice != null){
            bot.sendMessage(ChatId.fromId(update.message!!.chat.id), "Great! Now, How would you like to name your sticker?")
            return ExecutionOutput(State.GET_VOICE, update.message!!.voice!!.fileId)
        }

        return null
    }
}