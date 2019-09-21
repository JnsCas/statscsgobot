package com.jnscas.pinhead.model;

import com.jnscas.statscsgo.model.UserStats;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class ContextBot {

    private UserStats userStats;
    private Update update;
    private Message message;
    private Long chatId;

    public ContextBot(UserStats userStats,
                      Update update) {
        this.userStats = userStats;
        this.update = update;
        this.message = update.getMessage();
        this.chatId = update.getMessage().getChatId();
    }

    public UserStats user() {
        return userStats;
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

    public String getFromUsernameOrFirstName() {
        User fromUser = update.getMessage().getFrom();
        String userName = fromUser.getUserName();
        return userName == null ? fromUser.getFirstName() : userName;
    }

    @Override
    public String toString() {
        return "ContextBot{" +
                "user=" + userStats +
                ", update=" + update +
                ", message=" + message +
                ", chatId=" + chatId +
                '}';
    }
}
