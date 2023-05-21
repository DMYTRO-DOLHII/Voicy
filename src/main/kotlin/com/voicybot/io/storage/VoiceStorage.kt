package com.voicybot.io.storage

import com.voicybot.io.bot.Voice

class VoiceStorage : Storage<String, Voice>() {

    private var maximum = 1
    private var used = 0


    public fun getKey(name: String): String? {
        for ((key, value) in this.getStorage()) {
            if (value.getName() == name) {
                return key
            }
        }
        return null
    }

    fun limitReached(): Boolean {
        return (used < maximum)
    }

    fun newVoice() {
        used++
    }

    fun deleteVoice(){
        used--
    }

    fun newLimit( newMax: Int){
        maximum = newMax
    }

    fun getUsed(): Int{
        return used
    }

    fun getMaximum(): Int{
        return maximum
    }
}