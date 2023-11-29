package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private final Long id;
    private final SessionDuration sessionDuration;
    private SessionEnrolment sessionEnrolment;
    private final CoverImage coverImage;

    public Session(Long id, SessionDuration sessionDuration, SessionEnrolment sessionEnrolment, CoverImage coverImage) {
        this.id = id;
        this.sessionDuration = sessionDuration;
        this.sessionEnrolment = sessionEnrolment;
        this.coverImage = coverImage;
    }

    public void enrolment(NsUser student, Long userPayed) {
        if (isFree()) {
            sessionEnrolment.freeEnrolment(student);
            return;
        }

        sessionEnrolment.payEnrolment(student, userPayed);
    }

    public int totalStudentCount() {
        return this.sessionEnrolment.totalStudent();
    }

    public boolean isMaxStudent() {
        return this.sessionEnrolment.isFullStudents();
    }

    private boolean isFree() {
        return this.sessionEnrolment.isFree();
    }
}
