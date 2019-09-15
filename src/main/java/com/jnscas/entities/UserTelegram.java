package com.jnscas.entities;

import com.jnscas.pendinginputs.PendingInput;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

import java.util.Optional;

public class UserTelegram {

    private ObjectId id;
    private String userName;
    private String pendingInputName;

    /**
     * public constructor for mongodb
     */
    public UserTelegram() {

    }

    public UserTelegram(String userName,
                        Optional<PendingInput> pendingInput) {
        this.userName = userName;
        this.pendingInputName = pendingInput.map(PendingInput::getPendingInputName).orElse(null);
    }

    public String getUserName() {
        return userName;
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
