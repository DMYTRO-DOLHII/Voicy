package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State

class MyVoices : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().message!!.text.toString() == "/myvoices"){
            return ExecutionOutput(State.MY_VOICES, "${input.id()}")
        }

        return null
    }
}