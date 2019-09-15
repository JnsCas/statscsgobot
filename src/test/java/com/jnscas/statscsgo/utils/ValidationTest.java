package com.jnscas.statscsgo.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {

    @Test
    public void isValidSteamId() {
        boolean result = Validation.isValidSteamId("12345678901234567");
        assertTrue(result);
    }

    @Test
    public void isNotValidSteamId() {
        boolean result = Validation.isValidSteamId("12345678q01234567");
        assertTrue(!result);
    }

}