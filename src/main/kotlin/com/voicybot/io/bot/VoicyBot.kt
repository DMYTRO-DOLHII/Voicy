package com.voicybot.io.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.inlinequeryresults.InlineQueryResult
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.StateMachine
import com.voicybot.io.storage.UserStorage
import java.util.*

class VoicyBot(private var TOKEN: String) {

    private var users: UserStorage = UserStorage()
    private var machines = mutableMapOf<User, StateMachine>()

    fun createBot(): Bot {
        return bot {
            token = TOKEN

            dispatch {
                inlineQuery {
                    println("-------------")
                    println("Handling inline query for user ${inlineQuery.from.id}")

                    val query: String = inlineQuery.query.trim()
                    val res = mutableListOf<InlineQueryResult.CachedVoice>()
                    var i = 0

                    if (query.isEmpty()) {

                        for (voice in users.get(inlineQuery.from.id)!!.getVoices().getStorage()) {
                            res.add(
                                i,
                                InlineQueryResult.CachedVoice(i.toString(), voice.key, voice.value.getName())
                            )
                            i++
                        }
                    } else {
                        for (voice in users.get(inlineQuery.from.id)!!.getVoices().getStorage()) {
                            if (voice.value.getName().lowercase(Locale.getDefault()).contains(query)) {
                                res.add(
                                    i,
                                    InlineQueryResult.CachedVoice(
                                        i.toString(),
                                        voice.key,
                                        voice.value.getName()
                                    )
                                )
                                i++
                            }
                        }
                    }

                    val result: List<InlineQueryResult.CachedVoice> = res

                    bot.answerInlineQuery(inlineQuery.id, result, cacheTime = 0)
                }

                command("start") {
                    if (users.get(message.chat.id) == null) {

                        val user = User(
                            message.chat.id,
                            message.chat.username.toString(),
                            message.chat.firstName.toString(),
                            message.chat.lastName.toString()
                        )
                        users.add(message.chat.id, user)
                        machines[user] = StateMachine()

                        println("User " + message.chat.id + " was added")
                    }

                }

                command("myvoices"){
                    print(users.get(message.from!!.id))
                }

                text {
                    if (!message.text.toString().startsWith("/")) {
                        println("-------------")
                        println("Handling text")
                        val user = users.get(message.chat.id)
                        val input = Input(update, update.message!!.chat.id)
                        machines[users.get(message.chat.id)]!!.execute(bot, input, user!!.getVoices())
                    }
                }

                message(Filter.Command) {
                    println("-------------")
                    println("Handling command")
                    val user = users.get(message.chat.id)
                    val input = Input(update, update.message!!.chat.id)
                    machines[user]!!.execute(bot, input, user!!.getVoices())
                }

                audio {
                    println("-------------")
                    println("Handling audio")
                    val user = users.get(message.chat.id)
                    val input = Input(update, update.message!!.chat.id)
                    machines[user]!!.execute(bot, input, user!!.getVoices())
                }

                voice {
                    println("-------------")
                    println("Handling invoice")
                    val user = users.get(message.chat.id)
                    val input = Input(update, update.message!!.chat.id)
                    machines[user]!!.execute(bot, input, user!!.getVoices())
                }

                callbackQuery {
                    println("-------------")
                    println("Handling callbackQuery")
                    val user = users.get(callbackQuery.from.id)
                    val input = Input(update, update.callbackQuery!!.from.id)
                    machines[user]!!.execute(bot, input, user!!.getVoices())
                }
            }
        }
    }

}