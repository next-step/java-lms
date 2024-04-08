package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private int maxNumber;

    private Status status;

    private long price;

    private LocalDateTime startedAt;

    private LocalDateTime endAt;

    private List<NsUser> attendees = new ArrayList<>();

    public Session(final int maxNumber, final Status status, final long price, final LocalDateTime endAt) {
        this(maxNumber, status, price, LocalDateTime.now(), endAt);
    }

    public Session(final int maxNumber, final Status status, final long price, final LocalDateTime startedAt,
                   final LocalDateTime endAt) {
        this.maxNumber = maxNumber;
        this.status = status;
        this.price = price;
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    public void enroll(final NsUser user, final long price) {
        validate(price);
        attendees.add(user);
    }

    private void validate(final long price) {
        validatePrice(price);
        validateAttendeeNumber();
        validateRecruitingStatus();
        validateStartedAt();
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
        if (status != Status.RECRUITING) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    private void validateStartedAt() {
        if (startedAt.isBefore(LocalDateTime.now())) {
            status = Status.END;
            throw new IllegalArgumentException("시작일이 지난 강의는 수강 신청할 수 없습니다.");
        }
    }

    public List<NsUser> getAttendees() {
        return attendees;
    }
}
