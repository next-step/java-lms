package nextstep.courses.domain.session;

import nextstep.courses.domain.registration.EnrollmentOpenType;
import nextstep.courses.domain.registration.SessionEnrollment;
import nextstep.users.domain.Student;
import nextstep.users.domain.Students;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;
    private Long courseId;
    private String title;
    private String cover;
    private int cardinalNumber;
    private SessionCostType sessionCostType;
    private SessionEnrollment sessionEnrollment;
    private SessionPeriod sessionPeriod;

    Session(Long courseId, String title, String cover, int cardinalNumber, SessionCostType sessionCostType, EnrollmentOpenType enrollmentOpenType, SessionState sessionState, int maxUser, LocalDateTime startDate, LocalDateTime endDate) {
        this(0L, courseId, title, cover, cardinalNumber, sessionCostType, enrollmentOpenType, sessionState, maxUser, startDate, endDate);
    }

    Session(Long id, Long courseId, String title, String cover, int cardinalNumber, SessionCostType sessionCostType, EnrollmentOpenType enrollmentOpenType, SessionState sessionState, int maxUser, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.cover = cover;
        this.cardinalNumber = cardinalNumber;
        this.sessionCostType = sessionCostType;
        this.sessionEnrollment = new SessionEnrollment(sessionState, enrollmentOpenType, maxUser);
        this.sessionPeriod = new SessionPeriod(startDate, endDate);
    }

    public static Session of(Long id, Long courseId, String title, String cover, int cardinalNumber, SessionCostType sessionCostType, EnrollmentOpenType enrollmentOpenType, SessionState sessionState, int maxUser, LocalDateTime startDate, LocalDateTime endDate) {
        return new Session(id, courseId, title, cover, cardinalNumber, sessionCostType, enrollmentOpenType, sessionState, maxUser, startDate, endDate);
    }

    public static Session of(Long courseId, String title, String cover, int cardinalNumber, SessionCostType sessionCostType, EnrollmentOpenType enrollmentOpenType, SessionState sessionState, int maxUser, LocalDateTime startDate, LocalDateTime endDate) {
        return new Session(courseId, title, cover, cardinalNumber, sessionCostType, enrollmentOpenType, sessionState, maxUser, startDate, endDate);
    }

    public Students enroll(Student student) {
        return sessionEnrollment.enroll(student);
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public int getCardinalNumber() {
        return cardinalNumber;
    }

    public SessionCostType getSessionCostType() {
        return sessionCostType;
    }

    public SessionEnrollment getSessionEnrollment() {
        return sessionEnrollment;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
