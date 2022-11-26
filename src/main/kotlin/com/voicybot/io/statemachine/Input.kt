package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.entities.Update

class Input(
    private val update: Update,
    private val id : Long
) {

    fun update(): Update{
        return update
    }

    fun id(): Long{
        return id
    }
}