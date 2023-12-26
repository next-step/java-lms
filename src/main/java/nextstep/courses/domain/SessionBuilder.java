package nextstep.courses.domain;

import static nextstep.courses.domain.CourseBuilder.aCourse;
import static nextstep.courses.domain.CoverImageBuilder.aCoverImage;

import java.time.LocalDateTime;

public class SessionBuilder extends AuditInfo {
    private Long id = 0L;
    private Course course = aCourse().build();
    private SessionPayment sessionPayment = new SessionPayment();
    private Enrollment enrollment = new Enrollment();
    private Duration duration = new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
    private SessionStatus sessionStatus = SessionStatus.READY;
    private CoverImage coverImage = aCoverImage().build();

    private SessionBuilder(){
        super(LocalDateTime.now(), null);
    }

    private SessionBuilder(final SessionBuilder sessionBuilder){
        this();
        this.id = sessionBuilder.id;
        this.course = sessionBuilder.course;
        this.sessionPayment = sessionBuilder.sessionPayment;
        this.enrollment = sessionBuilder.enrollment;
        this.duration = sessionBuilder.duration;
        this.sessionStatus = sessionBuilder.sessionStatus;
        this.coverImage = sessionBuilder.coverImage;
    }

    public static SessionBuilder aSession(){
        return new SessionBuilder();
    }

    public SessionBuilder withId(final Long id){
        this.id = id;
        return new SessionBuilder(this);
    }

    public SessionBuilder withCourse(final Course course){
        this.course = course;
        return new SessionBuilder(this);
    }

    public SessionBuilder withSessionPayment(final SessionPayment sessionPayment){
        this.sessionPayment = sessionPayment;
        return new SessionBuilder(this);
    }

    public SessionBuilder withEnrollment(final Enrollment enrollment){
        this.enrollment = enrollment;
        return new SessionBuilder(this);
    }

    public SessionBuilder withDuration(final Duration duration){
        this.duration = duration;
        return new SessionBuilder(this);
    }

    public SessionBuilder withSessionStatus(final SessionStatus sessionStatus){
        this.sessionStatus = sessionStatus;
        return new SessionBuilder(this);
    }

    public SessionBuilder withCoverImage(final CoverImage coverImage){
        this.coverImage = coverImage;
        return new SessionBuilder(this);
    }

    public SessionBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return new SessionBuilder(this);
    }

    public SessionBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return new SessionBuilder(this);
    }

    public Session build(){
        return new Session(id, course, sessionPayment, duration, sessionStatus, coverImage, createdAt, updatedAt);
    }
}
