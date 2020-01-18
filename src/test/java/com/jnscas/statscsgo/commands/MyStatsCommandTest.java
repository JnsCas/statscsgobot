package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.StatsResolver;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.steam.SteamClient;
import com.jnscas.statscsgo.steam.api.responses.PlayerStatsDTO;
import com.jnscas.statscsgo.steam.api.responses.TestPlayerStatsDTO;
import com.jnscas.statscsgo.steam.api.responses.UserStatsCsGoResponse;
import org.junit.Before;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyStatsCommandTest {

    MyStatsCommand command;
    SteamClient steamClient;

    @Before
    public void init() {
        this.steamClient = mock(SteamClient.class);
        this.command = new MyStatsCommand(new StatsResolver(), steamClient);
    }

    @Test
    public void myStatsCommandOK() {
        ContextBot contextBot = mock(ContextBot.class);
        UserStats userStats = new UserStats(123);
        userStats.setSteamId64("123456");
        when(contextBot.user()).thenReturn(userStats);

        PlayerStatsDTO playerStatsDTO = new TestPlayerStatsDTO()
                .totalKills(100)
                .totalKillsHeadshot(70)
                .totalDeaths(2)
                .mvps(50)
                .build();

        UserStatsCsGoResponse response = new UserStatsCsGoResponse();
        response.setPlayerstats(playerStatsDTO);

        when(steamClient.getUserStatsCsGo(userStats)).thenReturn(response);

        SendMessage sendMessage = command.executeCommand(contextBot);

        assertFalse(sendMessage.getText().isEmpty());
        assertTrue(sendMessage.getText().contains("K/D Ratio: 50,00"));
        assertTrue(sendMessage.getText().contains("Headshot %: 70,0"));
        assertTrue(sendMessage.getText().contains("MVPs: 50"));
    }

}
