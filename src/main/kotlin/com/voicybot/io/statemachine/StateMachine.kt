package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.applier.AddVoice
import com.voicybot.io.statemachine.applier.CallVoice
import com.voicybot.io.statemachine.applier.GetVoice
import com.voicybot.io.statemachine.state.State

class StateMachine {

    private var configuration: StateMachineConfiguration
    private var current: State

    init {
        this.configuration = StateMachineConfiguration()
        current = State.START

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
            listOf(State.CALL_VOICE)
        )

        configuration.setUpState(
            State.CALL_VOICE,
            CallVoice(),
            listOf()
        )
    }

    public fun execute(message: Message, bot: Bot): String{
        val result = configuration.getApplier(current).apply(bot, message)

        if(result == null){
            bot.sendMessage(ChatId.fromId(message.chat.id), "Sorry, but it is now available now!")
        }

        current = result!!.getState()

        return result.getContent()
    }

}