package nextstep.courses.domain;

public interface JoinStrategy {
    boolean joinable(Session session, long payAmount);
}
