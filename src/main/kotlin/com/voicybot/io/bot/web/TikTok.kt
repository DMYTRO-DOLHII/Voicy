package com.voicybot.io.bot.web

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.jsoup.Jsoup
import java.io.InputStream
import java.net.CookieManager
import java.util.*

object TikTok {

    fun video(link: String): InputStream {
        val cookieStore = CookieManager().cookieStore
        val document = Jsoup.connect(link)
            .cookieStore(cookieStore)
            .get()

        val foundTags = document.body().select("#SIGI_STATE")
        val scriptTag = foundTags.single()
        val json = scriptTag.data()

        val videoUrl = extractVideoUrl(json).orElseGet { extractSoundUrl(json).get() }

        return Jsoup.connect(videoUrl)
            .referrer("https://www.tiktok.com/")
            .cookieStore(cookieStore)
            // Jsoup expects text types only and throws exception if receives any other types.
            .ignoreContentType(true)
            .execute()
            .bodyStream()

    }

    private fun extractVideoUrl(json: String): Optional<String> {
        val objectMapper = jacksonObjectMapper()
        val jsonNode = objectMapper.readTree(json)
        val directVideoUrl: String
        try {
            val videoId = jsonNode
                .get("ItemList")
                .get("video")
                .get("keyword")
                .asText()
            directVideoUrl = jsonNode.get("ItemModule")
                .get(videoId)
                .get("music")
                .get("playUrl")
                .asText()
        } catch (e: NullPointerException) {
            return Optional.empty()
        }

        if (directVideoUrl.isNotEmpty()){
            return Optional.of(directVideoUrl)
        }
        return Optional.empty()
    }

    private fun extractSoundUrl(json: String): Optional<String> {
        val objectMapper = jacksonObjectMapper()
        val jsonNode = objectMapper.readTree(json)
        val directSoundUrl: String
        try {
            val itemId = jsonNode
                .get("ItemList")
                .get("music")
                .get("list")
                .get(0)
                .asText()
            directSoundUrl = jsonNode.get("ItemModule")
                .get(itemId)
                .get("music")
                .get("playUrl")
                .asText()
        } catch (e: NullPointerException) {
            return Optional.empty()
        }
        if (directSoundUrl.isNotEmpty()) {
            return Optional.of(directSoundUrl)
        }
        return Optional.empty()
    }
}