package com.voicybot.io.bot

import com.github.kotlintelegrambot.Bot
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

    private var handlingVoice = Voice()

    public fun run(bot: Bot, message: Message){
        val result = stateMachine.execute(bot, message)

        if(result != ""){
            val isReady = handlingVoice.prepare(result)

            if(isReady){
                voiceStorage.add(handlingVoice.getId(), handlingVoice)

                handlingVoice = Voice()
            }
        }
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