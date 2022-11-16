package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.voicybot.io.bot.Voice
import com.voicybot.io.statemachine.state.State
import com.voicybot.io.storage.VoiceStorage

class OutputExecutor {

    private var handlingVoice = Voice()

    public fun execute(bot: Bot, output: ExecutionOutput, storage: VoiceStorage) {
        if (output.getState() == State.DELETE_VOICE)
            return voiceList(bot, output, storage)

        if (output.getState() == State.DELETE_GET_VOICE)
            return deleteVoice(output, storage)

        if (output.getContent() != "")
            return createVoice(output, storage)
    }

    private fun voiceList(bot: Bot, output: ExecutionOutput, storage: VoiceStorage) {
        val inlineKeyboardButton = mutableListOf<List<InlineKeyboardButton>>()

        for ((key, value) in storage.getStorage()) {
            inlineKeyboardButton.add(
                listOf(
                    InlineKeyboardButton.CallbackData(
                        text = value.getName(),
                        callbackData = value.getName()
                    )
                )
            )
        }

        val inlineKeyboardMarkup = InlineKeyboardMarkup.create(inlineKeyboardButton)

        bot.sendMessage(
            chatId = ChatId.fromId(output.getContent().toLong()),
            text = "Your stickers : ",
            replyMarkup = inlineKeyboardMarkup
        )

        // TODO delete after problem will be solved
        for ((key, value) in storage.getStorage()) println("$key : ${value.getName()}")
    }

    private fun deleteVoice(output: ExecutionOutput, storage: VoiceStorage) {
        val key = storage.getKey(output.getContent())
        println(key)
        if (key != null) {
            storage.delete(key)
        }
    }

    private fun createVoice(output: ExecutionOutput, storage: VoiceStorage) {
        val isReady = handlingVoice.prepare(output.getContent())

        if (isReady) {
            storage.add(handlingVoice.getId(), handlingVoice)

            handlingVoice = Voice()
        }
    }
}
