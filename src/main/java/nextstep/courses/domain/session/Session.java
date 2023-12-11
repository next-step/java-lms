package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.SystemTimeStamp;
import nextstep.courses.domain.image.SessionImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Session {
    private Long sessionId;
    private String title;
    private SessionType sessionType;
    private Course course;
    private List<NsUser> students;
    private SessionImage sessionImage;
    private SessionPlan sessionPlan;
    private SystemTimeStamp systemTimeStamp;

    public static Session valueOf(String title, String course) {
        return new Session(0L, title, new Course(course, 1L), SessionType.FREE
                , new SessionPlan(SessionStatus.fromDate(LocalDate.now(), LocalDate.now()), LocalDate.now(), LocalDate.now())
                , new SystemTimeStamp(LocalDateTime.now(), LocalDateTime.now()));
    }

    public static Session dateOf(String title, String course, LocalDate startDate, LocalDate endDate) {
        return new Session(0L, title, new Course(course, 1L), SessionType.FREE
                , new SessionPlan(SessionStatus.fromDate(startDate, endDate), startDate, endDate)
                , new SystemTimeStamp(LocalDateTime.now(), LocalDateTime.now()));
    }

    public Session(Long sessionId, String title, Course course, SessionType sessionType, SessionPlan sessionPlan, SystemTimeStamp systemTimeStamp) {
        this.sessionId = sessionId;
        this.title = title;
        this.course = course;
        this.students = new ArrayList<>(Collections.emptyList());
        this.sessionType = sessionType;
        this.sessionPlan = sessionPlan;
        this.sessionImage = null;
        this.systemTimeStamp = systemTimeStamp;
    }

    public void signUp(NsUser student, Payment payment) throws CannotSignUpException {
        validateSessionStatus();
        validatePayInfo(student, payment);
        students.add(student);
    }

    private void validatePayInfo(NsUser student, Payment payment) throws CannotSignUpException {
        if (payment.getSessionId() != sessionId) {
            throw new CannotSignUpException("결제한 강의정보가 맞지 않습니다.");
        }
        if (student.getId() != payment.getNsUserId()) {
            throw new CannotSignUpException("결제자와 신청자의 정보가 일치하지 않습니다.");
        }
    }

    private void validateSessionStatus() throws CannotSignUpException {
        if (!SessionStatus.canSignUp(sessionPlan.getStartDate(), sessionPlan.getEndDate())) {
            throw new CannotSignUpException("강의를 신청할 수 있는 기간이 아닙니다.");
        }
    }

    public void saveImage(SessionImage sessionImage) {
        this.sessionImage = sessionImage;
    }

    public void cancel() {

    }

    public int getStudentCount() {
        return students.size();
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
