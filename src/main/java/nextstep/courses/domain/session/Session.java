package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.domain.session.constant.SessionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private Long id;
    private final SessionRange sessionRange;
    private final SessionType sessionType;
    private final int maxCapacity;
    private final Long tuition;
    private SessionStatus sessionStatus;
    private CoverImage coverImage;
    private List<Enrollment> enrollments = new ArrayList<>();

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, String sessionType, String sessionStatus, CoverImage coverImage) {
        this(id, startDate, endDate, sessionType, Integer.MAX_VALUE, 0L, sessionStatus, coverImage);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, String sessionType, int maxCapacity, Long tuition, String sessionStatus, CoverImage coverImage) {
        this(id, startDate, endDate, SessionType.from(sessionType.toUpperCase()), maxCapacity, tuition, SessionStatus.from(sessionStatus.toUpperCase()), coverImage);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, int maxCapacity, Long tuition, SessionStatus sessionStatus, CoverImage coverImage) {
        this(id, new SessionRange(startDate, endDate), sessionType, maxCapacity, tuition, sessionStatus, coverImage);
    }

    public Session(Long id, SessionRange sessionRange, SessionType sessionType, int maxCapacity, Long tuition, SessionStatus sessionStatus, CoverImage coverImage) {
        this.id = id;
        this.sessionRange = sessionRange;
        this.sessionType = sessionType;
        this.maxCapacity = maxCapacity;
        this.tuition = tuition;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
    }

    public void addEnrollment(Enrollment enrollment) {
        validatePaymentAmount(enrollment);
        validateNotDuplicate(enrollment);
        validateNotFull();
        validateSessionStatus();
        this.enrollments.add(enrollment);
    }

    private void validatePaymentAmount(Enrollment enrollment) {
        if(sessionType.equals(SessionType.PAID)){
            enrollment.isPaymentAmount(tuition);
        }
    }

    private void validateNotDuplicate(Enrollment enrollment) {
        if (this.enrollments.contains(enrollment)) {
            throw new IllegalArgumentException("이미 신청한 강의입니다.");
        }
    }

    private void validateNotFull() {
        if (this.maxCapacity <= this.enrollments.size()) {
            throw new IllegalArgumentException("수강인원이 초과했습니다.");
        }
    }

    private void validateSessionStatus() {
        if (!this.sessionStatus.equals(SessionStatus.ACTIVE)) {
            throw new IllegalArgumentException("현재는 강의 모집중이 아닙니다.");
        }
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public long getTuition() {
        return tuition;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

}
