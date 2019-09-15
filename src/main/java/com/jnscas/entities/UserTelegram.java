package com.jnscas.entities;

import com.jnscas.pendinginputs.PendingInput;

import java.util.Optional;
import org.bson.types.ObjectId;

public class UserTelegram {

    private ObjectId id;
    private String userName;
    private Optional<PendingInput> pendingInput;

    public UserTelegram(String userName) {
        this.userName = userName;
        this.pendingInput = Optional.empty();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Optional<PendingInput> getPendingInput() {
        return pendingInput;
    }

    public void setPendingInput(Optional<PendingInput> pendingInput) {
        this.pendingInput = pendingInput;
    }
}
