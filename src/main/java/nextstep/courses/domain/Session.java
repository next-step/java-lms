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
    private final SessionStatus status = SessionStatus.CLOSED;
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

    public Long price() {
        return price;
    }

    public Integer capacity() {
        return capacity;
    }

    public void addParticipant(String participant) {
        if (this.participants.size() >= capacity) {
            throw new BusinessInvalidValueException("최대수강인원을 초과했습니다.");
        }
        this.participants.add(participant);
    }
}
