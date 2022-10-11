package com.voicybot.io.bot

import com.voicybot.io.statemachine.StateMachine
import com.voicybot.io.statemachine.state.State
import com.voicybot.io.storage.VoiceStorage

class User(
    private var id: String,
    private var username: String,
    private var firstName: String,
    private var lastName: String) {


    private var voiceStorage = VoiceStorage()
    private var stateMachine = StateMachine()
    private lateinit var state: State


    public fun addVoice(voice: Voice){
        voiceStorage.add(voice)
    }

    public fun deleteVoice(i: Int) : Voice? {
        return voiceStorage.delete(i)
    }

    public fun getId(): String{
        return id
    }

    public fun getUsername(): String{
        return username
    }

    public fun getFirstName(): String{
        return firstName
    }

    public fun getLastName(): String{
        return lastName
    }
}