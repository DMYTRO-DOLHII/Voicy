package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.applier.AddVoice
import com.voicybot.io.statemachine.applier.GetVoice
import com.voicybot.io.statemachine.state.State

class StateMachine {

    private var configuration: StateMachineConfiguration
    private var state: State

    init {
        this.configuration = StateMachineConfiguration()
        state = State.START

        configuration.setUpState(
            State.START,
            null,
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
            listOf(State.GET_VOICE)
        )
    }

    public fun execute(message: Message): String{
        return ""
    }

    public fun apply(): State?{
        return null
    }
}