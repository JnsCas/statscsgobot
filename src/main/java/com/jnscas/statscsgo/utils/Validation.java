package com.jnscas.statscsgo.utils;

import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.exceptions.NotRegisteredExceptionStatsCsGoBot;
import com.jnscas.statscsgo.model.UserStats;

public class Validation {

    public static boolean isValidSteamId(String messageText) {
        return messageText.length() == 17 && messageText.matches("[0-9]\\d*");
    }

    public static void signInRequired(ContextBot contextBot) {
        UserStats userStats = (UserStats) contextBot.user();
        if (!userStats.isAlreadyRegistered()) {
            throw new NotRegisteredExceptionStatsCsGoBot(contextBot.getFromUsernameOrFirstName());
        }
    }
}
