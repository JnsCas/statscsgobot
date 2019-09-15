package com.jnscas.pendinginputs.impl;

import com.jnscas.pendinginputs.PendingInput;
import com.jnscas.utils.SendMessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class StartPendingInput implements PendingInput {

    private static final Logger logger = Logger.getLogger(StartPendingInput.class.toString());

    private static Map<String, Boolean> pendingByUser = new HashMap<>();

    @Override
    public boolean hasUserActivePending(String userName) {
        return pendingByUser.getOrDefault(userName, false);
    }

    @Override
    public SendMessage resolve(Update update) {
        String userName = update.getMessage().getFrom().getUserName();
        Long chatId = update.getMessage().getChatId();
        String steamId = update.getMessage().getText();
        if (isValidSteamId(steamId)) {
            //steamIdByUser.put(userName, steamId); FIXME actualizar mongo
            //pendingSignUpByUser.put(userName, false); FIXME actualizar mongo
            logger.info(String.format("Sign up ok for username: %s", userName));
            return SendMessageBuilder.newBuilder()
                    .chatId(chatId)
                    .userName(userName)
                    .messageText("Sign up OK!")
                    .build();
        } else {
            return SendMessageBuilder.newBuilder()
                    .chatId(chatId)
                    .userName(userName)
                    .messageText("SteamID64 invalid. Try again")
                    .build();
        }
    }

    private boolean isValidSteamId(String messageText) { //TODO create unit test
        return messageText.length() == 17 && messageText.matches("\"-?(0|[1-9]\\\\d*)\"");
    }
}
