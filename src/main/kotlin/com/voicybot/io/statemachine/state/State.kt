package com.voicybot.io.statemachine.state

enum class State {

    START,

    ADD_VOICE,
    GET_VOICE,
    CALL_VOICE,

    TAG_VOICE,
    GET_TAGS,

    ERROR,

    EXECUTION_END
}