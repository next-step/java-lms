package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exception.BusinessInvalidValueException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session extends BaseEntity {
    public static long FREE_PRICE = 0L;
    public static int MAX_CAPACITY = Integer.MAX_VALUE;
    private final Long id;
    private final Course course;
    private final SessionCover sessionCover;
    private final Period period;
    private final Capacity capacity;
    private final Price price;

    private SessionStatus status = SessionStatus.PREPARE;
    private final List<NsUser> participants = new ArrayList<>();

    public Session(Long id, LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover, Course course, Capacity capacity, Price price) {
        this.id = id;
        this.period = new Period(beginDt, endDt);
        this.sessionCover = sessionCover;
        this.course = course;
        this.capacity = capacity;
        this.price = price;
    }

    public Session(Long id, LocalDateTime beginDt, LocalDateTime endDt, SessionStatus status, SessionCover sessionCover, Capacity capacity, Price price, Course course) {
        this.id = id;
        this.period = new Period(beginDt, endDt);
        this.status = status;
        this.sessionCover = sessionCover;
        this.course = course;
        this.capacity = capacity;
        this.price = price;
    }

    public static Session ofFree(Long id, LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover, Course course) {
        return new Session(id, beginDt, endDt, sessionCover, course, new Capacity(MAX_CAPACITY), new Price(FREE_PRICE));
    }

    public static Session ofPaid(Long id, LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover, Course course, Long price, Integer capacity) {
        return new Session(id, beginDt, endDt, sessionCover, course, new Capacity(capacity), new Price(price));
    }

    public void startEnrollment() {
        this.status = SessionStatus.ENROLL;
    }

    public void validateStatus() {
        if (SessionStatus.ENROLL != this.status) {
            throw new BusinessInvalidValueException("수강신청 가능한 상태가 아닙니다.");
        }
    }

    public void enroll(NsUser participant, Long amount) {
        capacity.validateCapacity(participants.size());
        price.validatePrice(amount);
        validateStatus();
        this.participants.add(participant);
    }

    public Price price() {
        return price;
    }

    public Capacity capacity() {
        return capacity;
    }

    public Long id() {
        return id;
    }

    public Period period() {
        return period;
    }

    public Course course() {
        return course;
    }

    public SessionCover sessionCover() {
        return sessionCover;
    }

    public String status() {
        return this.status.name();
    }
}
