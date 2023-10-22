package me.jon.peidaibot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.ByteBuffer;

public class AudioForward implements AudioSendHandler {

    private final AudioPlayer player;
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);
    private final MutableAudioFrame frame = new MutableAudioFrame();

    public AudioForward(AudioPlayer player) {
        this.player = player;
        frame.setBuffer(buffer);
    }

    public boolean canProvide() {
        return player.provide(frame);
    }

    public ByteBuffer provide20MsAudio() {
        return buffer.flip();
    }

    public boolean isOpus() {
        return AudioSendHandler.super.isOpus();
    }
}
