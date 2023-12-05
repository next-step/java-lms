package nextstep.sessions.domain;

import nextstep.images.domain.Image;

import java.time.LocalDateTime;

public class PaySession extends Session{
    private final int fee;
    private final int limit;
    private int studentCount;

    public PaySession(Long id, LocalDateTime startedDate, LocalDateTime endedDate, Image image, SessionType sessionType, int fee, int limit) {
        super(id, startedDate, endedDate, image, sessionType);
        this.fee = fee;
        this.limit = limit;
        this.studentCount = INIT_COUNT;
    }

    public void enrolment(int payMoney) {
        isEnrolmentPossible();
        validateMoney(payMoney);
        validateLimit();
        this.studentCount++;
    }

    private void validateLimit() {
        if (limit == studentCount) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }
    }

    private void validateMoney(int payMoney) {
        if (payMoney != fee) {
            throw new IllegalArgumentException("강의 가격과 결제 가격이 다릅니다.");
        }
    }
    public int studentCount() {
        return this.studentCount;
    }
}
