package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.applier.*
import com.voicybot.io.statemachine.state.State
import kotlin.math.log

class StateMachine {

    private var configuration: StateMachineConfiguration = StateMachineConfiguration()
    private var current: State

    init {
        current = State.INITIAL

        configuration.setUpState(
            State.INITIAL,
            Initial(),
            listOf(State.START)
        )

        configuration.setUpState(
            State.START,
            Start(),
            listOf(State.ADD_VOICE, State.TAG_VOICE)
        )

        configuration.setUpState(
            State.ADD_VOICE,
            AddVoice(),
            listOf(State.GET_VOICE)
        )

        configuration.setUpState(
            State.GET_VOICE,
            GetVoice(),
            listOf(State.CALL_VOICE)
        )

        configuration.setUpState(
            State.CALL_VOICE,
            CallVoice(),
            listOf(State.ADD_VOICE, State.TAG_VOICE)
        )
    }

    public fun execute(bot: Bot, message: Message): String {

        println("User ${message.chat.id} now in ${current} state")

        val result = tryToApply(bot, message, current)

        if (result == null) {
            bot.sendMessage(ChatId.fromId(message.chat.id), "Sorry, but it is now available now!")
        } else {
            current = result.getState()
        }

        println("User ${message.chat.id} has new ${current} state")

        return result!!.getContent()
    }

    private fun tryToApply(bot: Bot, message: Message, current: State): ExecutionOutput? {
        for (next: State in configuration.getNextStates(current)) {
            val res = configuration.getApplier(next).apply(bot, message)

            if (res!!.getState() != null) {
                return res
            }
        }

        return null
    }

}