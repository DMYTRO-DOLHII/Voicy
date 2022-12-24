package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class Start : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().message!!.text.equals("/start")){
            bot.sendMessage(ChatId.fromId(input.id()), "Hello there!\nMy name is Voicy. I can guide you in " +
                    "voice sticker creation. Just use commands below and follow further instructions.\n" +
                    "/start - See starting instruction\n" +
                    "/addvoice - Start voice sticker creation\n" +
                    "/deletevoice - Delete sticker\n" +
                    "/backtomain - Return to the main menu\n\n" +
                    "Use @VoicySticker_bot to see and use your stickers.\nEnjoy!")

            return ExecutionOutput(State.START, "")
        }

        return null
    }
}