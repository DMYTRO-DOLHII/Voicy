package com.voicybot.io.statemachine.applier

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.voicybot.io.bot.web.TikTok
import com.voicybot.io.statemachine.ExecutionOutput
import com.voicybot.io.statemachine.Input
import com.voicybot.io.statemachine.state.State
import org.bytedeco.ffmpeg.global.avcodec
import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.FFmpegFrameRecorder
import org.bytedeco.javacv.Frame
import java.io.ByteArrayOutputStream
import java.io.InputStream

class GetVoice : Applier {
    override fun apply(bot: Bot, input: Input): ExecutionOutput? {
        if (input.update().message!!.audio != null) {
            bot.sendMessage(
                ChatId.fromId(input.id()),
                "Great! Now, How would you like to name your sticker?"
            )
            return ExecutionOutput(State.GET_VOICE, input.update().message!!.audio!!.fileId)
        }

        if (input.update().message!!.voice != null) {
            bot.sendMessage(
                ChatId.fromId(input.id()),
                "Great! Now, How would you like to name your sticker?"
            )
            return ExecutionOutput(State.GET_VOICE, input.update().message!!.voice!!.fileId)
        }

        if (isTikTok(input.update().message!!.text!!)){
            bot.sendMessage(ChatId.fromId(input.id()), "Handling...")
            val message: Message = bot.sendVoice(ChatId.fromId(input.id()), toVoice(TikTok.video(input.update().message!!.text!!))).first!!.body()!!.result!!
            bot.sendMessage(
                ChatId.fromId(input.id()),
                "Great! Now, How would you like to name your sticker?"
            )

            return ExecutionOutput(State.GET_VOICE, message.voice!!.fileId)
        }


        return null
    }


    private fun isTikTok(link: String) : Boolean{
        return (link.contains("vm.tiktok.com") || link.contains("www.tiktok.com"))
    }

    private fun isYouTube(link: String) : Boolean{
        return false
    }


    private fun toVoice(audio: InputStream): ByteArray{
        val output = ByteArrayOutputStream()
        val recorder = FFmpegFrameRecorder(output, 2)
        recorder.setAudioOption("crf", "0")
        recorder.audioQuality = 0.0
        recorder.audioBitrate = 192000
        recorder.sampleRate = 48000
        recorder.audioChannels = 2
        recorder.format = "ogg"
        recorder.audioCodec = avcodec.AV_CODEC_ID_OPUS
        recorder.start()
        val grabber = FFmpegFrameGrabber(audio)
        grabber.start()
        //get audio sample and record it
        var f: Frame?
        while ((grabber.grabSamples().also { f = it }) != null) {
            recorder.record(f)
        }
        grabber.stop()
        recorder.close()
        return output.toByteArray()
    }
}