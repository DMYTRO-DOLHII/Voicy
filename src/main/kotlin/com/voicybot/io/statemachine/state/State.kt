package com.voicybot.io.statemachine.state

enum class State {

    INITIAL,

    START,

    ADD_VOICE,
    GET_VOICE,
    CALL_VOICE,

    TAG_VOICE,
    GET_TAGS,

    DELETE_VOICE,
    DELETE_GET_VOICE,


    ERROR,

    EXECUTION_END
}