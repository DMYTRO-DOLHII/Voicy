package com.voicybot.io.bot

class Voice() {

    private var id: Long = 0
    private lateinit var name: String
    private var tags = mutableListOf<String>()

    public fun prepare(content: String): Boolean {
        if (id == 0L) {
            setId(content.toLong())
        } else if (name == "") {
            setName(content)

            return true
        }

        return false
    }

    private fun setId(id: Long) {
        this.id = id
    }

    public fun getId(): Long {
        return id
    }

    private fun setName(name: String) {
        this.name = name
    }

    public fun getName(): String {
        return name
    }

    public fun addTags(tags: String) {

    }

    public fun getTags(): List<String> {
        return tags
    }
}