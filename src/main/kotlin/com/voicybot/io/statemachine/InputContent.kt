package com.voicybot.io.statemachine

class InputContent<Input>(
    private val input: Input
) {

    fun get(): Input{
        return input
    }
}