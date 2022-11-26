package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input

interface Applier {
    fun apply(bot: Bot, input: Input) : ExecutionOutput?
}