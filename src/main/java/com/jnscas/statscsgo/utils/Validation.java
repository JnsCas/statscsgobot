package com.jnscas.statscsgo.utils;

import com.jnscas.pinhead.model.ContextBot;
import com.jnscas.statscsgo.exceptions.NotRegisteredExceptionStatsCsGoBot;

public class Validation {

    public static boolean isValidSteamId(String messageText) {
        return messageText.length() == 17 && messageText.matches("[0-9]\\d*");
    }

    public static void signInRequired(ContextBot contextBot) { //TODO move to pinhead
        if (!contextBot.user().isAlreadyRegistered()) {
            throw new NotRegisteredExceptionStatsCsGoBot(contextBot.getFromUsernameOrFirstName());
        }
    }
}
