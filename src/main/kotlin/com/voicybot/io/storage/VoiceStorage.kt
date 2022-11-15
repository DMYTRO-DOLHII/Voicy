package com.voicybot.io.storage

import com.voicybot.io.bot.Voice

class VoiceStorage : Storage<String, Voice>(){
    public fun getKey(name: String): String? {
        for ((key, value) in this.getStorage()){
            if (value.getName() == name){
                return key
            }
        }
        return null
    }
}