package me.jon.peidaibot.commands;

import me.jon.peidaibot.ICommand;
import me.jon.peidaibot.lavaplayer.PlayerManage;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
public class PlayMus implements ICommand {


    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Will play song";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "name", "name of the song", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            event.reply("You need to be in a voice channel to play music").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            event.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());
        }
        else {
            if (selfVoiceState.getChannel() != memberVoiceState.getChannel()) {
                event.reply("You need to be in the same voice channel to play music.").queue();
                return;
            }
        }

        PlayerManage playerManage = PlayerManage.get();
        event.reply("Playing music").queue();
        playerManage.play(event.getGuild(), event.getOption("name").getAsString());
    }

}
