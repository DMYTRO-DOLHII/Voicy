package com.voicybot.io.bot

class Voice(private var id: String) {

    private var name: String = TODO()
    private var tags = mutableListOf<String>()

    public fun setName(name: String){
        this.name = name
    }

    public fun addTags(tags: String){

    }

    public fun getId(): String{
        return id
    }

    public fun getName(): String{
        return name
    }

    public fun getTags(): List<String>{
        return tags
    }
}