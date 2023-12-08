package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exception.BusinessInvalidValueException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    public static long FREE_PRICE = 0L;
    public static int MAX_CAPACITY = Integer.MAX_VALUE;
    public static Long SEQ = 0L;


    public final Long id;
    private final LocalDateTime beginDt;
    private final LocalDateTime endDt;
    private final Long price;
    private final Integer capacity;
    private SessionStatus status = SessionStatus.CLOSED;
    private final List<NsUser> participants = new ArrayList<>();
    private final SessionCover sessionCover;
    private final Course course;

    private Session(LocalDateTime beginDt, LocalDateTime endDt, Long price, Integer capacity, SessionCover sessionCover, Course course) {
        this.id = SEQ++;
        this.beginDt = beginDt;
        this.endDt = endDt;
        this.price = price;
        this.capacity = capacity;
        this.sessionCover = sessionCover;
        this.course = course;
    }

    public static Session ofFree(LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover, Course course) {
        return new Session(beginDt, endDt, FREE_PRICE, MAX_CAPACITY, sessionCover, course);
    }

    public static Session ofPaid(LocalDateTime beginDt, LocalDateTime endDt, Long price, Integer capacity, SessionCover sessionCover, Course course) {
        return new Session(beginDt, endDt, price, capacity, sessionCover, course);
    }

    public void enroll(NsUser participant, Long amount) {
        validateStatus();
        validateCapacity();
        validatePrice(amount);
        this.participants.add(participant);
    }

    public void startEnrollment() {
        this.status = SessionStatus.ENROLL;
    }

    public Long price() {
        return price;
    }

    public Integer capacity() {
        return capacity;
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

    private void validatePrice(long price) {
        if (this.price != price) {
            throw new BusinessInvalidValueException("강의 가격이 변동되었습니다.");
        }
    }
}
