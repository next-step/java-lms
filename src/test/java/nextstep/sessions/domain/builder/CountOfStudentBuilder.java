package nextstep.sessions.domain.builder;

import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.SessionType;

public class CountOfStudentBuilder {

    private final int currentCountOfStudents = 0;

    private final int maxOfStudents = 10;

    public CountOfStudent build() {
        return new CountOfStudent(currentCountOfStudents, maxOfStudents, SessionType.PAID);
    }

}
