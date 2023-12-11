package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.SessionImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class Session {
    private Long sessionId;
    private String title;
    private Course course;

    private SessionType sessionType;

    private SessionStatus sessionStatus;
    private int studentCount;

    private LocalDate startDate;
    private LocalDate endDate;

    private SessionImage sessionImage;

    public static Session titleOf(String title, String course) {
        return new Session(1L, title, new Course(course, 1L)
                , SessionType.FREE, SessionStatus.RECRUITING
                , LocalDate.now(), LocalDate.now());
    }
    public static Session valueOf(String title, String course, SessionStatus sessionStatus) {
        return new Session(1L, title, new Course(course, 1L)
                , SessionType.FREE, sessionStatus
                , LocalDate.now(), LocalDate.now());
    }

    public static Session statusOf(String title, String course, SessionStatus sessionStatus) {
        return new Session(1L, title, new Course(course, 1L)
                , SessionType.FREE, sessionStatus
                , LocalDate.now(), LocalDate.of(9999, 12, 31));
    }

    public static Session dateOf(String title, String course, LocalDate startDate, LocalDate endDate) {
        return new Session(1L, title, new Course(course, 1L)
                , SessionType.FREE, SessionStatus.RECRUITING
                , startDate, endDate);
    }

    public Session(Long sessionId, String title, Course course
            , SessionType sessionType, SessionStatus sessionStatus) {
        this(sessionId, title, course
                , sessionType, sessionStatus
                , LocalDate.now(), LocalDate.of(9999, 12, 31));
    }

    private Session(Long sessionId, String title, Course course
            , SessionType sessionType, SessionStatus sessionStatus
            , LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);
        this.sessionId = sessionId;
        this.title = title;
        this.course = course;
        this.studentCount = 0;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = null;
    }

    private static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("강의의 시작일자와 종료일자는 필수 정보입니다.");
        }
    }

    public void saveImage(SessionImage sessionImage) {
        this.sessionImage = sessionImage;
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
