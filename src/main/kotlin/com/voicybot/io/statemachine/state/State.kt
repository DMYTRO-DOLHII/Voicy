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

    MY_VOICES,

    BACK_TO_MAIN,

    ERROR,
    STAY,

    EXECUTION_END
}