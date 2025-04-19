package nextstep.courses.domain;

public interface Capacity {
    boolean hasRoom();

    int remaining();

    Capacity increase();

    int getCurrent();

    int getMax();
}
