package com.jnscas.pinhead.model;

import com.jnscas.statscsgo.model.User;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ContextBot {

    private User user;
    private Update update;
    private Message message;
    private Long chatId;

    public ContextBot(User user,
                      Update update) {
        this.user = user;
        this.update = update;
        this.message = update.getMessage();
        this.chatId = update.getMessage().getChatId();
    }

    public User user() {
        return user;
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
                "user=" + user +
                ", update=" + update +
                ", message=" + message +
                ", chatId=" + chatId +
                '}';
    }
}
