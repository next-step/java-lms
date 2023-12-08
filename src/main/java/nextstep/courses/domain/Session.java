package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exception.BusinessInvalidValueException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    public static long FREE_PRICE = 0L;
    public static int MAX_CAPACITY = Integer.MAX_VALUE;
    private final LocalDateTime beginDt;
    private final LocalDateTime endDt;
    private final Long price;
    private final Integer capacity;
    private SessionStatus status = SessionStatus.CLOSED;
    private final List<String> participants = new ArrayList<>();
    private final SessionCover sessionCover;

    private Session(LocalDateTime beginDt, LocalDateTime endDt, Long price, Integer capacity, SessionCover sessionCover) {
        this.beginDt = beginDt;
        this.endDt = endDt;
        this.price = price;
        this.capacity = capacity;
        this.sessionCover = sessionCover;
    }

    public static Session ofFree(LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover) {
        return new Session(beginDt, endDt, FREE_PRICE, MAX_CAPACITY, sessionCover);
    }

    public static Session ofPaid(LocalDateTime beginDt, LocalDateTime endDt, Long price, Integer capacity, SessionCover sessionCover) {
        return new Session(beginDt, endDt, price, capacity, sessionCover);
    }

    public void register(String participant) {
        validateStatus();
        validateCapacity();
        this.participants.add(participant);
    }

    public void startEnrollment() {
        this.status = SessionStatus.ENROLL;
    }

    private void validateStatus() {
        if (SessionStatus.ENROLL != this.status) {
            throw new BusinessInvalidValueException("수강신청 가능한 상태가 아닙니다.");
        }
    }

    private void validateCapacity() {
        if (this.participants.size() >= capacity) {
            throw new BusinessInvalidValueException("최대수강인원을 초과했습니다.");
        }
    }

    public Long price() {
        return price;
    }

    public Integer capacity() {
        return capacity;
    }
}
