package nextstep.sessions.domain;

import java.time.LocalDate;
import nextstep.sessions.domain.enrollment.Enrollment;
import nextstep.sessions.domain.enrollment.SessionEnrollment;
import nextstep.sessions.domain.image.SessionImage;

public class Session {

    public static final String ERROR_SESSION_NOT_OPEN = "모집중인 강의만 수강 신청 가능합니다.";
    public static final String ERROR_CAPACITY_EXCEEDED = "강의 정원이 초과되어 수강 신청할 수 없습니다.";
    public static final String ERROR_PAYMENT_AMOUNT_MISMATCH = "결제 금액이 강의 수강료와 일치하지 않습니다.";

    private Long id;

    private SessionInfo sessionInfo;

    private SessionEnrollment sessionEnrollment;

    Session(Long id, SessionInfo sessionInfo, SessionEnrollment sessionEnrollment) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.sessionEnrollment = sessionEnrollment;
    }

    public static Session paidLimited(Long id, LocalDate startDate, LocalDate endDate, int fee, int maxCapacity,
                                      SessionImage image) {
        SessionInfo info = new SessionInfo(new Period(startDate, endDate), image);
        SessionEnrollment enrollment = SessionEnrollment.paidLimited(fee, maxCapacity);
        return new Session(id, info, enrollment);
    }

    public static Session freeUnlimited(Long id, LocalDate startDate, LocalDate endDate, SessionImage image) {
        SessionInfo info = new SessionInfo(new Period(startDate, endDate), image);
        SessionEnrollment enrollment = SessionEnrollment.freeUnlimited();
        return new Session(id, info, enrollment);
    }

    public SessionStatus status() {
        return sessionEnrollment.status();
    }

    public void startRecruiting() {
        sessionEnrollment.startRecruiting();
    }

    public void enroll(Enrollment enrollment) {
        sessionEnrollment.enroll(enrollment);
    }

}
