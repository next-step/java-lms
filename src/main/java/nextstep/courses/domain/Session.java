package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exception.BusinessInvalidValueException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Session extends BaseEntity{
    final Long id;
    final Course course;
    final SessionCover sessionCover;
    final Period period;
    SessionStatus status = SessionStatus.PREPARE;
    final List<NsUser> participants = new ArrayList<>();

    public Session(Long id, LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover, Course course) {
        this.id = id;
        this.period = new Period(beginDt, endDt);
        this.sessionCover = sessionCover;
        this.course = course;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public abstract void enroll(NsUser participant, Long amount);

    public void startEnrollment() {
        this.status = SessionStatus.ENROLL;
    }

    public void validateStatus() {
        if (SessionStatus.ENROLL != this.status) {
            throw new BusinessInvalidValueException("수강신청 가능한 상태가 아닙니다.");
        }
    }

    public Long id() {
        return id;
    }
}
