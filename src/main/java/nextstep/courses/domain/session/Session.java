package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private Long id;
    private final SessionDuration sessionDuration;
    private final Image coverImage;
    private final int fee;
    private final int maxStudents;
    private SessionStatus sessionStatus = SessionStatus.READY;
    private final Enrollment enrollment;

    public Session(Long id, LocalDate startDate, LocalDate endDate, Image coverImage, int fee, int maxStudents) {
        this.id = id;
        this.sessionDuration = new SessionDuration(startDate, endDate);
        this.coverImage = coverImage;
        this.fee = fee;
        this.maxStudents = maxStudents;
        this.enrollment = new Enrollment(fee, maxStudents, sessionStatus);
    }

    public void enroll(Student student) {
        this.enrollment.enroll(student);
    }

    public void changeStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        this.enrollment.changeStatus(sessionStatus);
    }

    public Long getId() {
        return this.id;
    }

    public SessionStatus getStatus() {
        return this.sessionStatus;
    }
}
