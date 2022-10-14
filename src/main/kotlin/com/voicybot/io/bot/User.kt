package com.voicybot.io.bot

import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.statemachine.StateMachine
import com.voicybot.io.storage.VoiceStorage

class User(
    private var id: Long,
    private var username: String,
    private var firstName: String,
    private var lastName: String) {


    private var voiceStorage: VoiceStorage = VoiceStorage()
    private var stateMachine: StateMachine = StateMachine()

    public fun run(message: Message): String{
        return stateMachine.execute(message)
    }

    public fun addVoice(key:Long, voice: Voice){
        voiceStorage.add(key, voice)
    }

    public fun deleteVoice(key: Long) : Voice? {
        return voiceStorage.delete(key)
    }

    public fun getId(): Long{
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