package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private int maxNumber;

    private SessionStatus sessionStatus;

    private long price;

    private SessionPeriod sessionPeriod;

    private List<NsUser> attendees = new ArrayList<>();

    public Session(final SessionStatus sessionStatus) {
        this(Integer.MAX_VALUE, sessionStatus, 0L, LocalDateTime.now(), LocalDateTime.MAX);
    }

    public Session(final int maxNumber, final SessionStatus sessionStatus, final long price, final LocalDateTime endAt) {
        this(maxNumber, sessionStatus, price, LocalDateTime.now(), endAt);
    }

    public Session(final int maxNumber, final SessionStatus sessionStatus, final long price, final LocalDateTime startedAt, final LocalDateTime endAt) {
        this.maxNumber = maxNumber;
        this.sessionStatus = sessionStatus;
        this.price = price;
        this.sessionPeriod = new SessionPeriod(startedAt, endAt);
    }

    public void enroll(final NsUser user, final long price) {
        validate(price);
        attendees.add(user);
    }

    private void validate(final long price) {
        validatePrice(price);
        validateAttendeeNumber();
        validateRecruitingStatus();
        sessionPeriod.validateStartedAt();
    }

    private void validatePrice(final long price) {
        if (this.price != price) {
            throw new IllegalArgumentException("가격이 일치하지 않습니다.");
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

    public List<NsUser> getAttendees() {
        return attendees;
    }
}
