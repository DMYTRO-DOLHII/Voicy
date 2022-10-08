package com.voicybot.io.statemachine

import com.voicybot.io.statemachine.state.State

class StateMachine {

    private var configuration: StateMachineConfiguration

    init {
        configuration = StateMachineConfiguration()

        //TODO Set up configuration
    }

    public fun execute(){

    }

    public fun apply(): State?{
        return null
    }
}