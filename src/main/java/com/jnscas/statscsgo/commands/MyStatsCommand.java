package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.commands.Command;
import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.pinhead.utils.SendMessageBuilder;
import com.jnscas.statscsgo.StatsResolver;
import com.jnscas.statscsgo.factories.FactorySteamClient;
import com.jnscas.statscsgo.factories.FactoryUserDAO;
import com.jnscas.statscsgo.model.User;
import com.jnscas.statscsgo.persistence.UserDAO;
import com.jnscas.statscsgo.steam.api.SteamClient;
import com.jnscas.statscsgo.steam.api.responses.UserStatsCsGoResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.jnscas.statscsgo.steam.parser.SteamParser.parse;

public class MyStatsCommand implements Command {

    private UserDAO userDAO;
    private StatsResolver statsResolver;
    private SteamClient steamClient;

    public MyStatsCommand() {
        this.userDAO = FactoryUserDAO.create();
        this.statsResolver = new StatsResolver();
        this.steamClient = FactorySteamClient.create();
    }

    @Override
    public String name() {
        return "/mystats";
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
        String statsUserMessage = statsResolver.createStatsUserMessage(userStatsCsGoResponse.getPlayerstats().getStats());
        return SendMessageBuilder.newBuilder()
                .chatId(context.chatId())
                .userName(context.user().getUserName())
                .messageText(statsUserMessage)
                .build();
    }
}
