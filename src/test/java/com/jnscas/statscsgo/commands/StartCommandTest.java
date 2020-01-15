package com.jnscas.statscsgo.commands;

import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.model.UserStats;
import com.jnscas.statscsgo.persistence.UserStatsDAO;
import org.junit.Before;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartCommandTest {

    UserStatsDAO userStatsDAO;
    StartCommand command;

    @Before
    public void init() {
        this.userStatsDAO = mock(UserStatsDAO.class);
        this.command = new StartCommand(userStatsDAO);
    }

    @Test
    public void startCommandPendingInputForSignUpOK() {
        int telegramId = 123;
        ContextBot contextBot = mock(ContextBot.class);
        UserStats user = new UserStats(telegramId);
        when(contextBot.user()).thenReturn(user);
        when(contextBot.getFromUsernameOrFirstName()).thenReturn("user-test");

        when(userStatsDAO.findByTelegramId(telegramId)).thenReturn(Optional.empty());

        SendMessage sendMessage = command.executeCommand(contextBot);

        assertFalse(sendMessage.getText().isEmpty());
        assertEquals("@user-test: Insert your steamID64, please", sendMessage.getText());
    }

    @Test
    public void startCommandoInsertYourSteamIdOK() {
        int telegramId = 123;
        ContextBot contextBot = mock(ContextBot.class);
        UserStats user = new UserStats(telegramId);
        when(contextBot.user()).thenReturn(user);
        when(contextBot.getFromUsernameOrFirstName()).thenReturn("user-test");

        when(userStatsDAO.findByTelegramId(telegramId)).thenReturn(Optional.of(user));

        SendMessage sendMessage = command.executeCommand(contextBot);

        assertFalse(sendMessage.getText().isEmpty());
        assertEquals("@user-test: Insert your steamID64, please", sendMessage.getText());
    }

    @Test
    public void startCommandoAlreadySignUpOK() {
        int telegramId = 123;
        ContextBot contextBot = mock(ContextBot.class);
        UserStats user = new UserStats(telegramId);
        user.setSteamId64("123");
        when(contextBot.user()).thenReturn(user);
        when(contextBot.getFromUsernameOrFirstName()).thenReturn("user-test");

        when(userStatsDAO.findByTelegramId(telegramId)).thenReturn(Optional.of(user));

        SendMessage sendMessage = command.executeCommand(contextBot);

        assertFalse(sendMessage.getText().isEmpty());
        assertEquals("@user-test: is already sign up", sendMessage.getText());
    }
}