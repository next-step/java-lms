package nextstep.utils;

import java.util.concurrent.atomic.AtomicLong;

public class KeyMakerSequentialLong implements KeyMaker<Long> {
    private final AtomicLong atomicLong;

    public KeyMakerSequentialLong() {
        this.atomicLong = new AtomicLong();
    }

    public KeyMakerSequentialLong(Long initialValue) {
        this.atomicLong = new AtomicLong(initialValue);
    }

    @Override
    public Long generate() {
        return atomicLong.incrementAndGet();
    }
}
