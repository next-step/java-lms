package nextstep.sessions.domain;

public interface CapacityExceededCheck {

    boolean check(int currentCountOfStudents, int maxOfStudents);

}
