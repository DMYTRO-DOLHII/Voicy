package com.voicybot.io.bot.web

import java.io.*

object YouTube {
    fun downloadVideo(url: String): InputStream {
        val pythonScript =
            "src\\main\\kotlin\\com\\voicybot\\io\\bot\\web\\youtube.py" // replace with the path to your Python script
        val process = ProcessBuilder("venv/Scripts/python.exe", pythonScript, url)
            .redirectErrorStream(true)
            .start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        reader.forEachLine { println(it) }
        process.waitFor()

        val videoFile = File("video.mp4") // replace with the path to your video file
        return FileInputStream(videoFile)
    }
}
