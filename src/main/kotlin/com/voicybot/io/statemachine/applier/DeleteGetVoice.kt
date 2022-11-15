package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

class DeleteGetVoice : Applier {
    override fun apply(bot: Bot, message: Message): ExecutionOutput? {
        if (message != null){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Your sticker has been deleted!")
            return ExecutionOutput(State.DELETE_GET_VOICE, message.replyMarkup!!.inlineKeyboard[0][0].text)
        }

        return null;
    }
}