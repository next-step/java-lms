package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private final SessionPeriod period;
    private final SessionImage coverImage;
    private final SessionStatus status;
    private final Set<Long> enrolledStudentIds;
    private final SessionType sessionType;


    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage) {
        this(startDate, endDate, coverImage, SessionStatus.PREPARING, new HashSet<>());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status) {
        this(startDate, endDate, coverImage, status, new HashSet<>());
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, String status, Set<Long> enrolledStudentIds) {
        this(startDate, endDate, coverImage, SessionStatus.from(status), enrolledStudentIds);
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds) {
        this(new SessionPeriod(startDate, endDate), coverImage, status, enrolledStudentIds);
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionImage image, String status, int maximumCapacity, long fee) {
        this(new SessionPeriod(startDate, endDate), image, SessionStatus.from(status), new HashSet<>(), new PaidSessionType(maximumCapacity, fee));
    }

    public Session(SessionPeriod period, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds) {
        this(period, coverImage, status, enrolledStudentIds, new FreeSessionType());
    }

    public Session(SessionPeriod period, SessionImage coverImage, SessionStatus status, Set<Long> enrolledStudentIds, SessionType sessionType) {
        this.period = period;
        this.coverImage = coverImage;
        this.status = status;
        this.enrolledStudentIds = enrolledStudentIds;
        this.sessionType = sessionType;
    }

    public void enroll(Long studentId) {
        enroll(studentId, null);
    }

    public void enroll(Long studentId, Long paymentAmount) {
        if (!status.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청할 수 있다");
        }
        if (!sessionType.isValidPayment(paymentAmount)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }

        if (sessionType.isOverCapacity(enrolledStudentIds.size())) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
        }
        enrolledStudentIds.add(studentId);
    }

    public boolean isEnrolled(Long studentId) {
        return enrolledStudentIds.contains(studentId);
    }
}
