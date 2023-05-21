package com.voicybot.io.statemachine

import com.github.kotlintelegrambot.entities.Update
import com.voicybot.io.bot.User

class Input(
    private val update: Update,
    private val id : Long,
    private val user : User
) {

    fun update(): Update{
        return update
    }

    fun id(): Long{
        return id
    }

    fun user(): User {
        return user
    }
}