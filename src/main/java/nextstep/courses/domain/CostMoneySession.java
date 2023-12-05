package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.courses.exception.ExceedStudentsCountException;
import nextstep.courses.exception.PaymentMisMatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class CostMoneySession extends Session {

    private final Integer maxParticipants;
    private final Integer price;

    public CostMoneySession(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, Integer maxParticipants, SessionStatus sessionStatus, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        super(id, courseId, title, sessionImages, sessionType, sessionStatus, startAt, endAt);
        validate(price, maxParticipants);
        this.maxParticipants = maxParticipants;
        this.price = price;
    }

    public static CostMoneySession of(Long id, Long courseId, String title, SessionImages sessionImages, Integer maxParticipants, SessionStatus sessionStatus, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        return new CostMoneySession(id, courseId, title, sessionImages, SessionType.COST_MONEY, maxParticipants, sessionStatus, price, startAt, endAt);
    }

    private void validate(int price, int maxParticipants) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }

        if (maxParticipants < 0) {
            throw new IllegalArgumentException("정원은 음수일 수 없습니다.");
        }
    }

    public void registerCostMoneySession(NsUser nsUser, Payment payment) {
        validateRegister(payment);
        super.students.add(nsUser);
    }

    private void validateRegister(Payment payment) {
        super.validateSessionIsRegistering();

        if (super.getSessionType().equals(SessionType.COST_MONEY) && super.students.isMaxParticipants(this.maxParticipants)) {
            throw new ExceedStudentsCountException("정원이 초과되어 더 이상 신청할 수 없습니다.");
        }

        if (!payment.isPaidPriceSame(this.price)) {
            throw new PaymentMisMatchException("지불한 금액이 강의 가격과 일치하지 않습니다.");
        }
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public int getPrice() {
        return this.price;
    }
}
