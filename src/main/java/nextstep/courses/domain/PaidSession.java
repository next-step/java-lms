package nextstep.courses.domain;

import nextstep.courses.exception.BusinessInvalidValueException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    private final int capacity;
    private final long price;

    public PaidSession(Long id, LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover, Course course, int capacity, long price) {
        super(id, beginDt, endDt, sessionCover, course);
        this.capacity = capacity;
        this.price = price;
    }

    @Override
    public void enroll(NsUser participant, Long amount) {
        validateCapacity();
        validatePrice(amount);
        validateStatus();
        this.participants.add(participant);
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

    public long price() {
        return price;
    }

    public int capacity() {
        return capacity;
    }
}
