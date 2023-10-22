package me.jon.peidaibot;

import me.jon.peidaibot.commands.PlayMus;
import me.jon.peidaibot.commands.Skip;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        Token tester = new Token();
        Commands manager = new Commands();
        manager.add(new PlayMus());
        manager.add(new Skip());
        JDA jda = JDABuilder.createDefault(tester.getToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new Listen())
                .addEventListeners(manager).build();
        jda.getPresence().setActivity(Activity.playing("ARAM"));
    }
}
