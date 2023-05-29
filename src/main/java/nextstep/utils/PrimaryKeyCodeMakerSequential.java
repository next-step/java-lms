package nextstep.utils;

import java.util.concurrent.atomic.AtomicLong;

public class PrimaryKeyCodeMakerSequential implements PrimaryKeyCodeMaker {

    private AtomicLong atomicLong;

    public PrimaryKeyCodeMakerSequential() {
        this.atomicLong = new AtomicLong();
    }

    public void offset(Long offset) {
        this.atomicLong = new AtomicLong(offset + atomicLong.get());
    }

    @Override
    public String generate() {
        return Long.toString(atomicLong.incrementAndGet());
    }
}
