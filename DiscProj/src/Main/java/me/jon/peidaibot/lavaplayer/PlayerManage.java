package me.jon.peidaibot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

public class PlayerManage {
    private static PlayerManage INSTANCE;
    private Map<Long, MusicManager> musicManager = new HashMap<>();

    final AudioPlayerManager audioPlayerManager = new DefaultAudioPlayerManager();

    private PlayerManage() {
        audioPlayerManager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
    }

    public static PlayerManage get() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManage();
        }
        return INSTANCE;
    }

    public MusicManager getMusicManager(Guild guild) {
        return musicManager.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            MusicManager mscManager = new MusicManager(audioPlayerManager);

            guild.getAudioManager().setSendingHandler(mscManager.getAudioForwarder());

            return mscManager;
        });
    }

    public void play(Guild guild, String trackURL) {
        MusicManager gldMscManager = getMusicManager(guild);
        audioPlayerManager.loadItemOrdered(gldMscManager, trackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                gldMscManager.getTrackPlayer().queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException exception) {

            }
        });
    }

}
