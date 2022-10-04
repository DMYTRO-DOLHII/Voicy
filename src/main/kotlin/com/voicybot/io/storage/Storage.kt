package com.voicybot.io.storage

abstract class Storage<T> {

    private val storage: List<T> = TODO()


    public fun add(el: T){

    }

    public fun delete(i: Int): T? {
        return null
    }

    public fun get(i : Int): T?{
        return null
    }

}