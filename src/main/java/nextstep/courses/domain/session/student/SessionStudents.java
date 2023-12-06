package nextstep.courses.domain.session.student;

import nextstep.courses.exception.NotRegisterSession;
import java.util.ArrayList;
import java.util.List;

public class SessionStudents {

    private List<SessionStudent> sessionStudents;

    public SessionStudents() {
        this.sessionStudents = new ArrayList<>();
    }

    public boolean add(SessionStudent sessionStudent) throws NotRegisterSession {
        validateDuplicate(sessionStudent);

        return this.sessionStudents.add(sessionStudent);
    }

    private void validateDuplicate(SessionStudent sessionStudent) throws NotRegisterSession {
        if (sessionStudents.contains(sessionStudent)) {
            throw new NotRegisterSession("이미 해당 강의를 수강 중 입니다.");
        }
    }

    public boolean isExceed(int enrollmentCapacity) {
        return sessionStudents.size() >= enrollmentCapacity;
    }
}
