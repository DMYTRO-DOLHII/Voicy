package com.voicybot.io.bot.web

import org.jsoup.Jsoup
import java.io.InputStream
import java.net.CookieManager
import java.util.regex.Pattern

object TikTok {

    fun video(link: String): InputStream {
        val cookieStore = CookieManager().cookieStore
        val document = Jsoup.connect(link)
            .cookieStore(cookieStore)
            .get()

        val pattern = Pattern.compile("\"playAddr\":\"(.*?)\"")
        val matcher = pattern.matcher(document.toString())

        if (matcher.find()) {
            var videoUrl = matcher.group(1)
            videoUrl = videoUrl.replace("\\u002F", "/")


            return Jsoup.connect(videoUrl)
                .referrer("https://www.tiktok.com/")
                .cookieStore(cookieStore)
                // Jsoup expects text types only and throws exception if receives any other types.
                .ignoreContentType(true)
                .execute()
                .bodyStream()

        }

        println("Error appeared while something...")
        return InputStream.nullInputStream()
    }
}