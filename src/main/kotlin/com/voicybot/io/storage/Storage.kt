package com.voicybot.io.storage

abstract class Storage<T> {

    private var storage = mutableListOf<T>()

    public fun add(el: T) {
        storage.add(el)
    }

    public fun delete(i: Int): T? {
        return storage.removeAt(i)
    }

    public fun get(i: Int): T? {
        return storage[i]
    }

}