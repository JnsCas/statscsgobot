package com.jnscas.statscsgo.pendinginputs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StartPendingInputTest {

    StartPendingInput command;

    @Before
    public void init() {
        command = new StartPendingInput();
    }

    @Test
    public void isValidSteamId() {
        boolean result = command.isValidSteamId("12345678901234567");
        assertTrue(result);
    }

    @Test
    public void isNotValidSteamId() {
        boolean result = command.isValidSteamId("12345678q01234567");
        assertTrue(!result);
    }

}
