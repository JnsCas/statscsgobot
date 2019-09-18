package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import com.jnscas.statscsgo.StatsResolver;
import com.jnscas.statscsgo.factories.FactorySteamClient;
import com.jnscas.statscsgo.model.User;
import com.jnscas.statscsgo.steam.api.SteamClient;
import com.jnscas.statscsgo.steam.api.responses.UserStatsCsGoResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.jnscas.statscsgo.steam.parser.SteamParser.parse;

public class MyStatsDetailCommand implements Command {

    //TODO crear abstraccion con MyStatsCommand

    private StatsResolver statsResolver;
    private SteamClient steamClient;

    public MyStatsDetailCommand() {
        this.statsResolver = new StatsResolver();
        this.steamClient = FactorySteamClient.create();
    }

    @Override
    public String name() {
        return "/mystatsdetail";
    }

    @Override
    public SendMessage executeCommand(ContextBot context) {
        User user = context.user();
        if (!user.isAlreadyRegistered()) {
            return SendMessageBuilder.newBuilder()
                    .chatId(context.chatId())
                    .userName(user.getUserName())
                    .messageText("you are not registered. Did you execute '/start' command?")
                    .build();
        }
        String response = steamClient.getUserStatsCsGo(user);
        UserStatsCsGoResponse userStatsCsGoResponse = parse(response, UserStatsCsGoResponse.class);
        String statsDetailUserMessage = statsResolver.createStatsDetailUserMessage(userStatsCsGoResponse.getPlayerstats().getStats());
        return SendMessageBuilder.newBuilder()
                .chatId(context.chatId())
                .userName(context.user().getUserName())
                .messageText(statsDetailUserMessage)
                .build();
    }
}
