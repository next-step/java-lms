package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Course {
    private final List<Session> sessions;

    public Course(List<Session> sessions) {
        this.sessions = sessions;
    }

}
