package com.voicybot.io.bot

class Voice() {

    private var id: String = ""
    private var name: String = ""
    private var tags = mutableListOf<String>()

    public fun prepare(content: String): Boolean {
        if (id == "") {
            setId(content)
        } else if (name == "") {
            setName(content)
            return true
        }

        return false
    }

    private fun setId(id: String) {
        this.id = id
    }

    public fun getId(): String {
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