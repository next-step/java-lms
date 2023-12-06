package nextstep.courses.domain.session.student;

import java.util.ArrayList;
import java.util.List;

public class SessionStudents {

    private List<SessionStudent> sessionStudents;

    public SessionStudents() {
        this.sessionStudents = new ArrayList<>();
    }

    public boolean add(SessionStudent sessionStudent) {
        validateDuplicate(sessionStudent);

        return this.sessionStudents.add(sessionStudent);
    }

    private void validateDuplicate(SessionStudent sessionStudent) {
        if (sessionStudents.contains(sessionStudent)) {
            throw new IllegalArgumentException("이미 해당 강의를 수강 중 입니다.");
        }
    }

    public boolean isExceed(int studentsCapacity) {
        return sessionStudents.size() >= studentsCapacity;
    }
}
