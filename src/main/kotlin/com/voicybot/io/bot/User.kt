package com.voicybot.io.bot

import com.voicybot.io.storage.VoiceStorage

class User(id: String,
           username: String,
           firstName: String,
           lastName: String) {

    private var voiceStorage: VoiceStorage = TODO()

    public fun addVoice(voice: Voice){
        voiceStorage.add(voice)
    }

    public fun deleteVoice(i: Int) : Voice? {
        return voiceStorage.delete(i)
    }
}