package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.state.State

interface Applier {
    fun apply(bot: Bot, message: Message) : ExecutionOutput?
}