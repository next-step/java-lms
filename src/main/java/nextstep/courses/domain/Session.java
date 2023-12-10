package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.enumeration.SessionStatus;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class Session {
    private Long sessionId;
    private String title;
    private Course course;
    private SessionStatus sessionStatus;
    private int studentCount;

    private LocalDate startDate;
    private LocalDate endDate;

    private SessionImage sessionImage;

    public static Session titleOf(String title) {
        return new Session(1L, title, null, SessionStatus.RECRUITING
                , LocalDate.now(), LocalDate.now());
    }

    public static Session statusOf(String title, String course, SessionStatus sessionStatus) {
        return new Session(1L, title, new Course(course, 1L), sessionStatus
                , LocalDate.now(), LocalDate.of(9999, 12, 31));
    }

    public Session imageOf(SessionImage sessionImage) {
        Session session = this;
        session.sessionImage = sessionImage;
        return session;
    }

    public Session(Long sessionId, String title, Course course, SessionStatus sessionStatus) {
        this(sessionId, title, course, sessionStatus
                , LocalDate.now(), LocalDate.of(9999, 12, 31));
    }

    private Session(Long sessionId, String title, Course course, SessionStatus sessionStatus
            , LocalDate startDate, LocalDate endDate) {
        this.sessionId = sessionId;
        this.title = title;
        this.course = course;
        this.studentCount = 0;
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = null;
    }

    public void signUp(Payment payment) throws CannotSignUpException {
        validateSessionStatus();
        studentCount += 1;
    }

    private void validateSessionStatus() throws CannotSignUpException {
        if (this.sessionStatus.equals(SessionStatus.PREPARING)) {
            throw new CannotSignUpException("강의가 준비중으로 신청할 수 없습니다.");
        }
        if (this.sessionStatus.equals(SessionStatus.CLOSE)) {
            throw new CannotSignUpException("강의가 종료되어 신청할 수 없습니다.");
        }
    }

    public void changeSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public void cancel() {
        studentCount -= 1;
    }

    public int getStudentCount() {
        return this.studentCount;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public boolean hasImage() {
        return !(sessionImage == null);
    }

    public void toCourse(Course course) {
        this.course = course;
    }
}
