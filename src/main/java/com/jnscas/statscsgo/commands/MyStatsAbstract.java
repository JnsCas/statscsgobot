package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.steam.api.SteamClient;
import com.jnscas.statscsgo.steam.api.responses.UserStatsCsGoResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

import static com.jnscas.statscsgo.steam.parser.SteamParser.parse;

public abstract class MyStatsAbstract {

    private SteamClient steamClient;

    public MyStatsAbstract(SteamClient steamClient) {
        this.steamClient = steamClient;
    }

    protected abstract String createMyStats(Map<String, Map<String, Integer>> stats);

    protected SendMessage executeMyStats(ContextBot context) {
        UserStats userStats = context.user();
        if (!userStats.isAlreadyRegistered()) {
            return SendMessageBuilder.newBuilder()
                    .chatId(context.chatId())
                    .userName(context.getFromUsernameOrFirstName())
                    .messageText("you are not registered. Did you execute '/start' command?")
                    .build();
        }
        String response = steamClient.getUserStatsCsGo(userStats);
        UserStatsCsGoResponse userStatsCsGoResponse = parse(response, UserStatsCsGoResponse.class);
        String myStats = createMyStats(userStatsCsGoResponse.getPlayerstats().getStats());
        return SendMessageBuilder.newBuilder()
                .chatId(context.chatId())
                .userName(context.getFromUsernameOrFirstName())
                .messageText(myStats)
                .build();
    }

}
