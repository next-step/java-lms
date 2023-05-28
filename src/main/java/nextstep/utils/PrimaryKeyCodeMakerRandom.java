package nextstep.utils;

import java.util.UUID;

public class PrimaryKeyCodeMakerRandom implements PrimaryKeyCodeMaker {
    private static final PrimaryKeyCodeMakerRandom SINGLETON = new PrimaryKeyCodeMakerRandom();

    private PrimaryKeyCodeMakerRandom() {
    }

    public static PrimaryKeyCodeMakerRandom of() {
        return SINGLETON;
    }

    @Override
    public String generate() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
