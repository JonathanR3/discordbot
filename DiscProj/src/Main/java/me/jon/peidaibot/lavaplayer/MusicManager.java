package me.jon.peidaibot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class MusicManager {

    private TrackPlayer trackPlayer;
    private AudioForward audioPlayer;

    public MusicManager(AudioPlayerManager manager) {
        AudioPlayer player = manager.createPlayer();
        trackPlayer = new TrackPlayer(player);
        player.addListener(trackPlayer);
        audioPlayer = new AudioForward(player);
    }

    public TrackPlayer getTrackPlayer() {
        return trackPlayer;
    }

    public AudioForward getAudioForwarder() {
        return audioPlayer;
    }
}
