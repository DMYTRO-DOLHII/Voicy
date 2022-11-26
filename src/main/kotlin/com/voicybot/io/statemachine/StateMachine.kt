package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.voicybot.io.statemachine.applier.*
import com.voicybot.io.statemachine.state.State
import com.voicybot.io.storage.VoiceStorage

class StateMachine {

    private var configuration: StateMachineConfiguration = StateMachineConfiguration()
    private var current: State
    private var outputExecutor = OutputExecutor()

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
            listOf(State.BACK_TO_MAIN, State.ADD_VOICE, State.DELETE_VOICE)
        )

        configuration.setUpState(
            State.ADD_VOICE,
            AddVoice(),
            listOf(State.BACK_TO_MAIN, State.GET_VOICE)
        )

        configuration.setUpState(
            State.GET_VOICE,
            GetVoice(),
            listOf(State.BACK_TO_MAIN, State.CALL_VOICE)
        )

        configuration.setUpState(
            State.CALL_VOICE,
            CallVoice(),
            listOf(State.BACK_TO_MAIN, State.ADD_VOICE, State.DELETE_VOICE)
        )

        configuration.setUpState(
            State.DELETE_VOICE,
            DeleteVoice(),
            listOf(State.DELETE_GET_VOICE, State.BACK_TO_MAIN)
        )

        configuration.setUpState(
            State.DELETE_GET_VOICE,
            DeleteGetVoice(),
            listOf(State.BACK_TO_MAIN, State.ADD_VOICE, State.DELETE_VOICE)
        )

        configuration.setUpState(
            State.BACK_TO_MAIN,
            BackToMain(),
            listOf(State.BACK_TO_MAIN, State.ADD_VOICE, State.DELETE_VOICE)
        )


    }

    fun execute(bot: Bot, input: Input, storage: VoiceStorage) {

        println("User ${input.id()} last state is $current")

        val result = tryToApply(bot, input, current)

        if (result == null) {
            bot.sendMessage(ChatId.fromId(input.id()), "Sorry, but it is now available now!")
        } else {
            current = result.getState()
            outputExecutor.execute(bot, result, storage)
        }

        println("User ${input.id()} new state is $current ")
    }

    private fun tryToApply(bot: Bot, input: Input, current: State): ExecutionOutput? {
        println("All next stages : ${configuration.getNextStates(current)}")
        for (next: State in configuration.getNextStates(current)) {

            println("Current next state is $next")
            val res = configuration.getApplier(next).apply(bot, input)

            if (res != null) {
                return res
            }
        }

        return null
    }

}