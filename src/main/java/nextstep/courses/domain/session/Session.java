package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
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
    private long sessionId;
    private String title;

    private long courseId;
    private SessionType sessionType;
    private List<NsUser> students;
    private SessionImage sessionImage;
    private SessionPlan sessionPlan;
    private SystemTimeStamp systemTimeStamp;

    public static Session valueOf(long id, String title, long courseId, EnrollmentStatus enrollmentStatus
            , LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Session(id, title, courseId, SessionType.FREE
                , new SessionPlan(enrollmentStatus, startDate, endDate)
                , new SystemTimeStamp(createdAt, updatedAt));
    }

    public Session(Long sessionId, String title, long courseId, SessionType sessionType, SessionPlan sessionPlan, SystemTimeStamp systemTimeStamp) {
        this.sessionId = sessionId;
        this.title = title;
        this.courseId = courseId;
        this.students = new ArrayList<>(Collections.emptyList());
        this.sessionType = sessionType;
        this.sessionPlan = sessionPlan;
        this.sessionImage = null;
        this.systemTimeStamp = systemTimeStamp;
    }

    public void signUp(NsUser student, Payment payment) {
        validateSessionStatus();
        validatePayInfo(student, payment);
        students.add(student);
    }

    private void validatePayInfo(NsUser student, Payment payment) {
        if (payment.getSessionId() != sessionId) {
            throw new CannotSignUpException("결제한 강의정보가 맞지 않습니다.");
        }
        if (student.getId() != payment.getNsUserId()) {
            throw new CannotSignUpException("결제자와 신청자의 정보가 일치하지 않습니다.");
        }
    }

    private void validateSessionStatus() {
        if (!EnrollmentStatus.canSignUp(this.sessionPlan.getEnrollmentStatus())) {
            throw new CannotSignUpException("강의 모집중이 아닙니다.");
        }
    }

    public void saveImage(SessionImage sessionImage) {
        this.sessionImage = sessionImage;
    }

    public int getStudentCount() {
        return students.size();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public SessionPlan getSessionPlan() {
        return sessionPlan;
    }

    public SystemTimeStamp getSystemTimeStamp() {
        return systemTimeStamp;
    }

    public boolean hasImage() {
        return !(sessionImage == null);
    }

}
