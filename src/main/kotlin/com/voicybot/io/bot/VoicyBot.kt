package com.voicybot.io.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.inlinequeryresults.InlineQueryResult
import com.github.kotlintelegrambot.entities.inlinequeryresults.InputMessageContent
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.logging.LogLevel
import com.voicybot.io.storage.UserStorage
import okhttp3.Dispatcher

class VoicyBot(private var TOKEN: String) {

    private var users: UserStorage = UserStorage()

    public fun createBot(): Bot {
        return bot {
            token = TOKEN

            dispatch {
                inlineQuery {
                    println("-------------")
                    println("Handling inline query for user ${inlineQuery.from.id}")

                    val query: String = inlineQuery.query.trim()
                    val res: MutableList<InlineQueryResult.CachedVoice> =
                        mutableListOf<InlineQueryResult.CachedVoice>()
                    var i = 0

                    if (query.isEmpty()) {

                        for (voice in users.get(inlineQuery.from.id)!!.getVoices().getStorage()) {
                            res.add(
                                i,
                                InlineQueryResult.CachedVoice(i.toString(), voice.key.toString(), voice.value.getName())
                            )
                            i++
                        }
                    } else {
                        for (voice in users.get(inlineQuery.from.id)!!.getVoices().getStorage()) {
                            if (voice.value.getName().contains(query)){
                                res.add(
                                    i,
                                    InlineQueryResult.CachedVoice(
                                        i.toString(),
                                        voice.key.toString(),
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
                        users.add(
                            message.chat.id,
                            User(
                                message.chat.id,
                                message.chat.username.toString(),
                                message.chat.firstName.toString(),
                                message.chat.lastName.toString()
                            )
                        )

                        println("User " + message.chat.id + " was added")
                    }

                }

                text {
                    if (!message.text.toString().startsWith("/")) {
                        println("-------------")
                        println("Handling text")
                        users.get(message.chat.id)!!.run(bot, message)
                    }
                }

                message(Filter.Command) {
                    println("-------------")
                    println("Handling command")
                    users.get(message.chat.id)!!.run(bot, message)
                }

                audio {
                    println("-------------")
                    println("Handling audio")
                    users.get(message.chat.id)!!.run(bot, message)
                }

                voice {
                    println("-------------")
                    println("Handling invoice")
                    users.get(message.chat.id)!!.run(bot, message)
                }

                callbackQuery {
                    println("-------------")
                    println("Handling callbackQuery")
                    users.get(callbackQuery.from.id)!!.run(bot, callbackQuery.message!!)
                }
            }
        }
    }

}