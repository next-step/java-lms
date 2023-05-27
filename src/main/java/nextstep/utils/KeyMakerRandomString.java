package nextstep.utils;

import java.util.UUID;

public class KeyMakerRandomString implements KeyMaker<String> {
    @Override
    public String generate() {
        return UUID.randomUUID().toString().substring(0,8);
    }
}
