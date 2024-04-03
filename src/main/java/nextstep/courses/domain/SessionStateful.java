package nextstep.courses.domain;

public interface SessionStateful {
    boolean preparing();
    boolean recruiting();
    boolean finished();
}
