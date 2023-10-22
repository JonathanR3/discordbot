package me.jon.peidaibot;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class Listen extends ListenerAdapter {

    public void onReady(@NotNull ReadyEvent event) {
        Guild guild = event.getJDA().getGuildById("1146337553151172669");
        guild.upsertCommand("playmus", "plays a song").addOptions(new OptionData(OptionType.STRING, "url", "the link"));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                String people = "" +event.getJDA().getTextChannels().size() + " Text Channels";
                event.getJDA().getPresence().setActivity(Activity.watching(people));
            }
        }, 0, 20000 ); }
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        MessageChannel channel = event.getChannel();
        String msg = event.getMessage().getContentRaw().toLowerCase();
        String reply = "";
        switch (msg) {
            case "anime":
                reply = "REEEEEEEEE";
                break;
            case "college":
                reply = "its over";
                break;
            case "study":
                reply = "I'm so tired.......";
                break;
        }
        channel.sendMessage(reply).queue();
    }
}
