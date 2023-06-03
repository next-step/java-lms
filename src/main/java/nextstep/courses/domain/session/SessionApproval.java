package nextstep.courses.domain.session;

import nextstep.users.domain.Student;

public class SessionApproval {
    private Student student;
    private Sessions studentSessions;
    private Sessions selectionSessions;

    public SessionApproval(Student student, Sessions studentSessions, Sessions selectionSessions) {
        this.student = student;
        this.studentSessions = studentSessions;
        this.selectionSessions = selectionSessions;
    }

    public void validateRegistrationStatus() {
        if (!student.isSessionPending()) {
            throw new IllegalArgumentException(student.getSessionRegistrationStatus() + "되어 승인 불가합니다.");
        }
    }

    public void validateSelectionSessions() {
        if(!selectionSessions.isContainsSameSession(studentSessions)) {
            throw new IllegalArgumentException("해당 수강 대기생은 선발된 인원이 아닙니다.");
        }
    }
}
