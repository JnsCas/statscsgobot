package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import com.jnscas.statscsgo.StatsResolver;
import com.jnscas.statscsgo.factories.FactorySteamClient;
import com.jnscas.statscsgo.steam.api.SteamClient;
import com.jnscas.statscsgo.steam.api.responses.UserStatsCsGoResponse;
import com.jnscas.statscsgo.utils.Validation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.jnscas.statscsgo.steam.parser.SteamParser.parse;

public class MyStatsDetailCommand implements Command {

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
        Validation.signInRequired(context);
        String response = steamClient.getUserStatsCsGo(context.user());
        UserStatsCsGoResponse userStatsCsGoResponse = parse(response, UserStatsCsGoResponse.class);
        String myStats = statsResolver.createMyStatsDetailUserMessage(userStatsCsGoResponse.getPlayerstats().getStats());
        return SendMessageBuilder.newBuilder()
                .chatId(context.chatId())
                .userName(context.getFromUsernameOrFirstName())
                .messageText(myStats)
                .build();
    }
}
