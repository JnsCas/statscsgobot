package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import com.jnscas.pinhead.utils.Validation;
import com.jnscas.statscsgo.StatsResolver;
import com.jnscas.statscsgo.steam.SteamClient;
import com.jnscas.statscsgo.steam.api.responses.UserStatsCsGoResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MyStatsDetailCommand implements Command {

    private StatsResolver statsResolver;
    private SteamClient steamClient;

    public MyStatsDetailCommand(StatsResolver statsResolver,
                                SteamClient steamClient) {
        this.statsResolver = statsResolver;
        this.steamClient = steamClient;
    }

    @Override
    public String name() {
        return "/mystatsdetail";
    }

    @Override
    public SendMessage executeCommand(ContextBot context) {
        Validation.signInRequired(context);
        UserStatsCsGoResponse userStatsCsGoResponse = steamClient.getUserStatsCsGo(context.user());
        String myStats = statsResolver.createMyStatsDetailUserMessage(userStatsCsGoResponse.getPlayerstats().getStats());
        return SendMessageBuilder.newBuilder()
                .chatId(context.chatId())
                .userName(context.getFromUsernameOrFirstName())
                .messageText(myStats)
                .build();
    }
}
