package nextstep.utils;

import java.util.concurrent.atomic.AtomicLong;

public class KeyMakerSequentialLong implements KeyMaker {
    private final AtomicLong atomicLong;

    public KeyMakerSequentialLong() {
        this.atomicLong = new AtomicLong();
    }

    public KeyMakerSequentialLong(Long initialValue) {
        this.atomicLong = new AtomicLong(initialValue);
    }

    @Override
    public String generate() {
        return Long.toString(atomicLong.incrementAndGet());
    }
}
