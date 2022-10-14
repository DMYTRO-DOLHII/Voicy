package com.voicybot.io.storage

abstract class Storage<K, V> {

    private var storage = mutableMapOf<K, V>()

    public fun add(key: K, value: V) {
        storage[key] = value
    }

    public fun delete(key: K): V? {
        return storage.remove(key)
    }

    public fun get(key: K): V? {
        return storage[key]
    }

}