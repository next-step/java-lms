package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;

import java.time.LocalDate;

public class Session {
    private Long id;
    private final SessionDuration sessionDuration;
    private final Image coverImage;
    private final int fee;
    private final int maxStudents;
    private SessionProgressStatus sessionProgressStatus = SessionProgressStatus.READY;
    private SessionApplyStatus sessionApplyStatus = SessionApplyStatus.NOT_APPLYING;
    private final Enrollment enrollment;

    public Session(Long id, LocalDate startDate, LocalDate endDate, Image coverImage, int fee, int maxStudents) {
        this.id = id;
        this.sessionDuration = new SessionDuration(startDate, endDate);
        this.coverImage = coverImage;
        this.fee = fee;
        this.maxStudents = maxStudents;
        this.enrollment = new Enrollment(fee, maxStudents, sessionProgressStatus, sessionApplyStatus);
    }

    public void enroll(Student student) {
        this.enrollment.enroll(student);
    }

    public void changeProgressStatus(SessionProgressStatus sessionProgressStatus) {
        this.sessionProgressStatus = sessionProgressStatus;
        this.enrollment.changeProgressStatus(sessionProgressStatus);
    }

    public void changeApplyStatus(SessionApplyStatus sessionApplyStatus) {
        this.sessionApplyStatus = sessionApplyStatus;
        this.enrollment.changeApplyStatus(sessionApplyStatus);
    }

    public Long getId() {
        return this.id;
    }

    public SessionProgressStatus getStatus() {
        return this.sessionProgressStatus;
    }
}
