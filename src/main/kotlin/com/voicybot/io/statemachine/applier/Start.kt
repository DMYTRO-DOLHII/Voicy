package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.bot.VoicyBot
import com.voicybot.io.statemachine.state.State

class Start: Applier {
    override fun apply(bot: Bot, message: Message): State? {
        if(message.text.toString() == "/start"){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Let's make your conversations with " +
                    "the frieds more funny. Just create a voice sticker and send it to them!)")

            return State.EXECUTION_END
        }

        return null
    }


}