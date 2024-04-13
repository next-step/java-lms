package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Set;
import nextstep.courses.CanNotJoinSessionException;
import nextstep.courses.infrastructure.dto.LearnerDto;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidSession extends Session {

    private Long price;
    private Integer capacity;

    public PaidSession(LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage, Long price, Integer capacity, LocalDateTime createdAt) {
        super(startDate, endDate, coverImage, createdAt);
        this.price = price;
        this.capacity = capacity;
    }

    public PaidSession(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage, SessionStatus status, boolean isRecruiting, Long price, Integer capacity, LocalDateTime createdAt) {
        super(id, startDate, endDate, coverImage, status, isRecruiting, createdAt);
        this.price = price;
        this.capacity = capacity;
    }

    public PaidSession(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage, SessionStatus status, boolean isRecruiting, Set<NsUser> learners,
        LocalDateTime createdAt, LocalDateTime updatedAt, Long price, Integer capacity) {
        super(id, startDate, endDate, coverImage, status, isRecruiting, learners, createdAt, updatedAt);
        this.price = price;
        this.capacity = capacity;
    }

    @Override
    public LearnerDto join(NsUser learner) {
        return join(learner, null);
    }

    public LearnerDto join(NsUser learner, Payment payment) {
        validateJoinable(learner, payment);
        learners.add(learner);
        return new LearnerDto(learner.getId(), id);
    }

    private void validateJoinable(NsUser learner, Payment payment) {
        super.validateJoinable(learner);
        validateCapacity();
        validatePayment(learner, payment);
        validatePaidEnough(payment);
    }

    private void validateCapacity() {
        boolean exceedCapacity = capacity < learners.size() + 1;
        if (exceedCapacity) {
            throw new CanNotJoinSessionException("모집 정원을 초과했습니다");
        }
    }

    private void validatePayment(NsUser learner, Payment payment) {
        boolean notValidPayment = payment == null
            || !id.equals(payment.getSessionId())
            || !learner.getId().equals(payment.getNsUserId());
        if (notValidPayment) {
            throw new CanNotJoinSessionException("결제가 정상적으로 이루어지지 않았습니다");
        }
    }

    private void validatePaidEnough(Payment payment) {
        boolean paidNotEnough = payment.getAmount() < price;
        if (paidNotEnough) {
            throw new CanNotJoinSessionException("결제 금액이 부족합니다");
        }
    }

    public Long getPrice() {
        return price;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
