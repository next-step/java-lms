package nextstep.courses.domain;

import nextstep.courses.exception.PaymentException;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;

    private String title;

    private SessionState sessionState;

    private SessionType sessionType;

    private ImageInfo coverImage;

    private Students students;

    private Long fee;

    private SessionPeriod sessionPeriod;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Session(long id, SessionType sessionType, SessionState sessionState, Long fee, Students students, SessionPeriod sessionPeriod) {
        this(id, null, sessionType, sessionState, students, null, fee, sessionPeriod);
    }

    public static Session sessionWithImage(long id, ImageInfo imageInfo) {
        return new Session(id, null,null, SessionState.RECRUITING, null, imageInfo, null, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15)));
    }

    public static Session sessionWithState(long id, SessionState sessionState, SessionPeriod sessionPeriod) {
        return new Session(id, null,null, sessionState, null, null, null, sessionPeriod);
    }

    public static Session sessionWithStateAndType(long id, SessionType sessionType, SessionState sessionState, Students students) {
        return new Session(id, sessionType, sessionState, null, students, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15)));
    }

    public static Session recruitingPaidSession(long id, SessionType sessionType, SessionState sessionState, Long fee, Students students) {
        return new Session(id, sessionType, sessionState, fee, students, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15)));
    }

    public static Session recruitingSession(long id, SessionState sessionState, Students students) {
        return new Session(id, null,null, sessionState, students, null, null, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15)));
    }

    public Session(long id, String title, SessionType sessionType, SessionState sessionState, Students students, ImageInfo imageInfo, Long fee, SessionPeriod sessionPeriod) {

        sessionPeriod.checkSessionStatus(sessionState);

        this.id = id;
        this.title = title;
        this.sessionType = sessionType;
        this.sessionState = sessionState;
        this.students = students;
        this.fee = fee;
        this.coverImage = imageInfo;
        this.sessionPeriod = sessionPeriod;
        this.creatorId = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void enrollStudent(NsUser student, Payment payment) {

        if(!SessionState.isAbleToEnroll(sessionState)) {
            throw new SessionException("모집중인 강의가 아닙니다.");
        }

        if(sessionType == SessionType.PAID) {
            students.isOverCapacity();
            payment.isAbleToPayment(fee);
        }

        students.addStudent(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(title, session.title) && sessionState == session.sessionState && sessionType == session.sessionType && Objects.equals(coverImage, session.coverImage) && Objects.equals(students, session.students) && Objects.equals(fee, session.fee) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(creatorId, session.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sessionState, sessionType, coverImage, students, fee, sessionPeriod, creatorId);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sessionState=" + sessionState +
                ", sessionType=" + sessionType +
                ", coverImage=" + coverImage +
                ", students=" + students +
                ", fee=" + fee +
                ", sessionPeriod=" + sessionPeriod +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
