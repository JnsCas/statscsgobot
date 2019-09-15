package com.jnscas.model;

import com.jnscas.entities.UserTelegram;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ContextBot {

    private UserTelegram userTelegram;
    private Update update;
    private Message message;
    private Long chatId;

    public ContextBot(UserTelegram userTelegram,
                      Update update) {
        this.userTelegram = userTelegram;
        this.update = update;
        this.message = update.getMessage();
        this.chatId = update.getMessage().getChatId();
    }

    public UserTelegram userTelegram() {
        return userTelegram;
    }

    public Update update() {
        return update;
    }

    public Message message() {
        return message;
    }

    public Long chatId() {
        return chatId;
    }

    @Override
    public String toString() {
        return "ContextBot{" +
                "userTelegram=" + userTelegram +
                ", update=" + update +
                ", message=" + message +
                ", chatId=" + chatId +
                '}';
    }
}
