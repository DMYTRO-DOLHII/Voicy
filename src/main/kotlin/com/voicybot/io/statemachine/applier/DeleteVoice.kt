package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class DeleteVoice : Applier {
    override fun apply(bot: Bot, message: Message): ExecutionOutput? {
        if(message.text.toString() == "/deletevoice"){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Select the button with voice name," +
                    "that you would like to delete")


            return ExecutionOutput(State.DELETE_VOICE, "${message.from!!.id}")
        }

        return null
    }
}