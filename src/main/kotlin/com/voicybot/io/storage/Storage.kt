package com.voicybot.io.storage

abstract class Storage<K, V> {

    private var storage = mutableMapOf<K, V>()

    public fun add(key: K, value: V) {
        storage[key] = value
    }

    public fun delete(key: K, value: V): Boolean {
        return storage.remove(key, value)
    }

    public fun get(key: K): V? {
        return storage[key]
    }

    public fun getStorage() : Map<K, V>{
        return storage
    }

}