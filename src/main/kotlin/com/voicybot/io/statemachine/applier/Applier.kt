package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Update
import com.voicybot.io.statemachine.ExecutionOutput

interface Applier {
    fun apply(bot: Bot, update: Update) : ExecutionOutput?
}