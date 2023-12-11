package nextstep.courses.domain;

import nextstep.courses.exception.PaymentException;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;

    private String title;

    private SessionState sessionState;

    private SessionType sessionType;

    private ImageInfo coverImage;

    private Integer maxPersonnel;

    private int enrollCount = 0;

    private List<NsUser> students = new ArrayList<>();

    private Long fee;

    private SessionPeriod sessionPeriod;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Session(long id, SessionType sessionType, SessionState sessionState, Integer maxPersonnel, Long fee, SessionPeriod sessionPeriod) {
        this(id, null, sessionType, sessionState, maxPersonnel, null, fee, sessionPeriod);
    }

    private Session(long id, SessionType sessionType, SessionState sessionState, Integer maxPersonnel, List<NsUser> students, int enrollCount, SessionPeriod sessionPeriod) {
        this(id, null,sessionType, sessionState, maxPersonnel, null, null, sessionPeriod);
        this.students = students;
        this.enrollCount = enrollCount;
    }

    public Session(long id, SessionState sessionState, SessionPeriod sessionPeriod) {
        this(id, null,null, sessionState, null, null, null, sessionPeriod);
    }

    public Session(long id, SessionState sessionState, ImageInfo imageInfo, SessionPeriod sessionPeriod) {
        this(id, null,null, sessionState, null, imageInfo, null, sessionPeriod);
    }

    public static Session recruitingPaidSession(long id, SessionType sessionType, SessionState sessionState, Integer maxPersonnel, Long fee) {
        return new Session(id, sessionType, sessionState, maxPersonnel, fee, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15)));
    }

    public static Session recruitingSession(long id, SessionType sessionType, SessionState sessionState, Integer maxPersonnel, List<NsUser> students, int enrollCount) {
        return new Session(id, sessionType, sessionState, maxPersonnel, students, enrollCount, new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(15)));
    }

    public Session(long id, String title, SessionType sessionType, SessionState sessionState, Integer maxPersonnel, ImageInfo imageInfo, Long fee, SessionPeriod sessionPeriod) {

        sessionPeriod.checkSessionStatus(sessionState);

        this.id = id;
        this.title = title;
        this.sessionType = sessionType;
        this.sessionState = sessionState;
        this.maxPersonnel = maxPersonnel;
        this.fee = fee;
        this.coverImage = imageInfo;
        this.sessionPeriod = sessionPeriod;
        this.creatorId = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void enrollStudent(NsUser student, Payment payment) {
        if(sessionType == SessionType.PAID && enrollCount >= maxPersonnel) {
            throw new SessionException("최대 수강 인원을 초과하였습니다.");
        }

        if(!SessionState.isAbleToEnroll(sessionState)) {
            throw new SessionException("모집중인 강의가 아닙니다.");
        }

        if(payment != null && !payment.isEqualPaidFee(fee)) {
            throw new PaymentException("수강료가 지불한 금액과 일치하지 않습니다.");
        }

        students.add(student);
        enrollCount++;
    }

    public int getEnrollCount() {
        return enrollCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return getEnrollCount() == session.getEnrollCount() && Objects.equals(id, session.id) && Objects.equals(title, session.title) && sessionState == session.sessionState && sessionType == session.sessionType && Objects.equals(coverImage, session.coverImage) && Objects.equals(maxPersonnel, session.maxPersonnel) && Objects.equals(students, session.students) && Objects.equals(fee, session.fee) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(creatorId, session.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, sessionState, sessionType, coverImage, maxPersonnel, getEnrollCount(), students, fee, sessionPeriod, creatorId);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sessionState=" + sessionState +
                ", sessionType=" + sessionType +
                ", coverImage=" + coverImage +
                ", maxPersonnel=" + maxPersonnel +
                ", enrollCount=" + enrollCount +
                ", students=" + students +
                ", fee=" + fee +
                ", sessionPeriod=" + sessionPeriod +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
