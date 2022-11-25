package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Update
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
            listOf(State.BACK_TO_MAIN, State.DELETE_GET_VOICE)
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

    fun execute(bot: Bot, update: Update, storage: VoiceStorage) {

        println("User ${update.message!!.chat.id} last state is $current")

        val result = tryToApply(bot, update, current)

        if (result == null) {
            bot.sendMessage(ChatId.fromId(update.message!!.chat.id), "Sorry, but it is now available now!")
        } else {
            current = result.getState()
            outputExecutor.execute(bot, result, storage)
        }

        println("User ${update.message!!.chat.id} new state is $current ")
    }

    private fun tryToApply(bot: Bot, update: Update, current: State): ExecutionOutput? {
        println("All next stages : ${configuration.getNextStates(current)}")
        for (next: State in configuration.getNextStates(current)) {

            println("Current next state is $next")
            val res = configuration.getApplier(next).apply(bot,update)

            if (res != null) {
                return res
            }
        }

        return null
    }

}