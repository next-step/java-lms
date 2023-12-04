package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.NotRegisterSession;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionStudents {

    private List<SessionStudent> sessionStudents;

    public SessionStudents() {
        this.sessionStudents = new ArrayList<>();
    }

    public boolean add(Session session, NsUser student) throws NotRegisterSession {
        SessionStudent sessionStudent = new SessionStudent(session, student);
        validateDuplicate(student, sessionStudent);

        return this.sessionStudents.add(sessionStudent);
    }

    private void validateDuplicate(NsUser student, SessionStudent sessionStudent) throws NotRegisterSession {
        if (sessionStudents.contains(sessionStudent)) {
            throw new NotRegisterSession(String.format("%s 학생은 이미 해당 강의 수강 중 입니다.", student.getName()));
        }
    }

    public void validateLimit(int limit) throws NotRegisterSession {
        if (sessionStudents.size() >= limit) {
            throw new NotRegisterSession("현재 수강 가능한 모든 인원수가 채워졌습니다.");
        }
    }
}
