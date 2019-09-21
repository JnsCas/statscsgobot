package com.jnscas.pinhead.entities;

import com.jnscas.pinhead.pendinginputs.PendingInput;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

import java.util.Optional;

public class UserPinhead {

    private ObjectId id;
    private Integer telegramId;
    private String pendingInputName;

    /**
     * public constructor for mongodb
     */
    public UserPinhead() {

    }

    public UserPinhead(Integer telegramId,
                       Optional<PendingInput> pendingInput) {
        this.telegramId = telegramId;
        this.pendingInputName = pendingInput.map(PendingInput::getPendingInputName).orElse(null);
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }

    public void setPendingInputName(String pendingInputName) {
        this.pendingInputName = pendingInputName;
    }

    public String getPendingInputName() {
        return pendingInputName;
    }

    @BsonIgnore
    public void setPendingInput(PendingInput pendingInput) {
        this.pendingInputName = pendingInput.getPendingInputName();
    }

    @BsonIgnore
    public PendingInput getPendingInput() { //TODO make optional return
        try {
            if (pendingInputName != null) {
                PendingInput pendingInput = (PendingInput) Class.forName(pendingInputName).newInstance();
                return pendingInput;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @BsonIgnore
    public boolean pendingInputExists() {
        return pendingInputName != null;
    }
}
