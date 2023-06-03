package nextstep.courses.domain.registration;

import nextstep.users.domain.NsUser;

import java.util.Set;

public class SessionRegistration {
    private final SessionStatus sessionStatus;
    private final Students students;

    private SessionRegistration(SessionStatus sessionStatus, Students students) {
        this.sessionStatus = sessionStatus;
        this.students = students;
    }

    public static SessionRegistration of(SessionStatus sessionStatus, Students students) {
        return new SessionRegistration(sessionStatus, students);
    }

    public void addUser(NsUser user) {
        validateSessionStatus(sessionStatus);
        students.add(user);
    }

    private void validateSessionStatus(SessionStatus sessionStatus) {
        if (sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("해당 강의는 모집중이 아닙니다.");
        }
    }

    public Set<NsUser> getStudents() {
        return students.getUsers();
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxUserCount() {
        return students.getMaxUserCount();
    }
}
