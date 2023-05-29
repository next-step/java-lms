package nextstep.lms.domain;

import java.util.List;

public class Course {

    private List<Session> sessions;

    public Course(List<Session> sessions) {
        this.sessions = sessions;
    }
}
