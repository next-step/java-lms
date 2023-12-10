package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.PaidSessionException;
import nextstep.courses.exception.ParticipantsException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PaidSession extends Session {

    private Amount sessionFee;
    private int maxApplyCount;

    public PaidSession(long id, CoverImage image, LocalDate start, LocalDate end, SessionProgressState state, long fee, int max, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, image, start, end, state, createdAt, updatedAt);
        this.sessionFee = new Amount(fee);
        this.maxApplyCount = max;
    }

    public PaidSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState state, long fee, int max, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(image, start, end, state, createdAt, updatedAt);
        this.sessionFee = new Amount(fee);
        this.maxApplyCount = max;
    }

    public PaidSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState state, long fee, int max, LocalDateTime createdAt) {
        super(image, start, end, state, createdAt);
        this.sessionFee = new Amount(fee);
        this.maxApplyCount = max;
    }

    @Override
    public void apply(Payment payment) {
        validateState();
        validateFee(payment);
        validateMaxCount(maxApplyCount);
        this.participants = participants.add(payment.findUser());
    }


    private void validateFee(Payment payment) {
        if (sessionFee.isNotSameByLong(payment.findAmount())) {
            throw new PaidSessionException("수강생이 결제한 금액과 수강료가 일치하지 않습니다");
        }
    }

    public void validateMaxCount(int count) {
        if (participants.size() >= count) {
            throw new ParticipantsException("강의 최대 수강 인원을 초과할 수 없습니다.");
        }
    }

    public long sessionFee() {
        return sessionFee.value();
    }

    public int maxApplyCount() {
        return maxApplyCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaidSession that = (PaidSession) o;
        return maxApplyCount == that.maxApplyCount && Objects.equals(sessionFee, that.sessionFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sessionFee, maxApplyCount);
    }

}
