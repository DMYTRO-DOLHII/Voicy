package com.voicybot.io.statemachine

import com.voicybot.io.statemachine.applier.Applier
import com.voicybot.io.statemachine.state.State

class StateMachineConfiguration {

    private var transactions = mutableMapOf<State, List<State>>()
    private var handlers = mutableMapOf<State, Applier>()

    public fun setUpState(state: State, applier: Applier?, nextStates: List<State>){
        transactions[state] = nextStates
        handlers[state] = applier
    }

    public fun getNextStates(active: State): List<State> {
        return transactions[active]!!
    }

    public fun getApplier(active: State): Applier{
        return handlers[active]!!
    }
}