package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private int maxNumber;

    private SessionStatus sessionStatus;

    private SessionPrice sessionPrice1;

    private SessionPeriod sessionPeriod;

    private List<NsUser> attendees = new ArrayList<>();

    public Session(final int maxNumber, final SessionStatus sessionStatus, final long price, final LocalDateTime endAt) {
        this(maxNumber, sessionStatus, price, LocalDateTime.now(), endAt);
    }

    public Session(final int maxNumber, final SessionStatus sessionStatus, final long price, final LocalDateTime startedAt, final LocalDateTime endAt) {
        this.maxNumber = maxNumber;
        this.sessionStatus = sessionStatus;
        this.sessionPrice1 = new SessionPrice(price);
        this.sessionPeriod = new SessionPeriod(startedAt, endAt);
    }

    public void enroll(final NsUser user, final long price) {
        validate(user, price);
        attendees.add(user);
    }

    public static Session free(final SessionStatus sessionStatus) {
        return new Session(Integer.MAX_VALUE, sessionStatus, 0L, LocalDateTime.now(), LocalDateTime.MAX);
    }

    private void validate(final NsUser user, final long price) {
        validateNonFreeSession();
        sessionPrice1.validatePriceEquality(price);
        validateAttendeeNumber();
        validateRecruitingStatus();
        validateAlreadyEnroll(user);
    }

    private void validateNonFreeSession() {
        if (sessionPrice1.isNotFree()) {
            sessionPeriod.validateStartedAt();
        }
    }

    private void validateAttendeeNumber() {
        if (attendees.size() >= maxNumber) {
            throw new IllegalArgumentException("수강 인원이 초과되었습니다.");
        }
    }

    private void validateRecruitingStatus() {
        if (sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    private void validateAlreadyEnroll(final NsUser user) {
        if (attendees.contains(user)) {
            throw new IllegalArgumentException("이미 수강 신청한 사용자입니다.");
        }
    }

    public List<NsUser> getAttendees() {
        return attendees;
    }
}
