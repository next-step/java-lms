package nextstep.utils;

import java.util.UUID;

public class KeyMakerRandomString implements KeyMaker {
    private static final KeyMakerRandomString SINGLETON = new KeyMakerRandomString();
    private KeyMakerRandomString() {
    }

    public static KeyMakerRandomString of() {
        return SINGLETON;
    }

    @Override
    public String generate() {
        return UUID.randomUUID().toString().substring(0,8);
    }
}
