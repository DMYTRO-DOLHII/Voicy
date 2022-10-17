package com.voicybot.io.statemachine

import com.voicybot.io.statemachine.state.State

class ExecutionOutput(
    private var state: State,
    private var content: String
) {

    public fun getState(): State{
        return state
    }

    public fun getContent(): String{
        return content
    }

}