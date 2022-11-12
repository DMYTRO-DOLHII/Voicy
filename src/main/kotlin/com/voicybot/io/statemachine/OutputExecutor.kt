package com.voicybot.io.statemachine

import com.voicybot.io.bot.Voice
import com.voicybot.io.statemachine.state.State
import com.voicybot.io.storage.VoiceStorage

class OutputExecutor {

    private var handlingVoice = Voice()

    public fun execute(output: ExecutionOutput, storage: VoiceStorage){
        if (output.getState() == State.DELETE_GET_VOICE){
            storage.delete(output.getContent())
        }

        if(output.getContent() != ""){
            val isReady = handlingVoice.prepare(output.getContent())

            if(isReady){
                storage.add(handlingVoice.getId(), handlingVoice)

                handlingVoice = Voice()
            }
        }
    }
}