package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.SessionImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private Long sessionId;
    private String title;
    private Course course;

    private SessionType sessionType;

    private SessionStatus sessionStatus;
    private int studentCount;
    private List<NsUser> students;

    private LocalDate startDate;
    private LocalDate endDate;

    private SessionImage sessionImage;

    public static Session valueOf(String title, String course) {
        return new Session(1L, title, new Course(course, 1L), SessionType.FREE,
                SessionStatus.fromDate(LocalDate.now(), LocalDate.now()), LocalDate.now(), LocalDate.now());
    }

    public static Session dateOf(String title, String course, LocalDate startDate, LocalDate endDate) {
        return new Session(1L, title, new Course(course, 1L), SessionType.FREE, SessionStatus.fromDate(startDate, endDate), startDate, endDate);
    }

    public Session(Long sessionId, String title, Course course, SessionType sessionType, SessionStatus sessionStatus, LocalDate startDate, LocalDate endDate) {
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

    private void validateSessionStatus() throws CannotSignUpException {
        if (!SessionStatus.canSignUp(startDate, endDate)) {
            throw new CannotSignUpException("강의를 신청할 수 있는 기간이 아닙니다.");
        }
    }

    public void saveImage(SessionImage sessionImage) {
        this.sessionImage = sessionImage;
    }

    public void signUp(Payment payment) throws CannotSignUpException {
        validateSessionStatus();
        studentCount += 1;
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
