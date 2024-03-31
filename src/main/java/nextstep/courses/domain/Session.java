package nextstep.courses.domain;

import static nextstep.courses.domain.SessionStatus.RECRUIT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CanNotJoinSessionException;
import nextstep.courses.InvalidSessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionCoverImage coverImage;
    private Long price;
    private Integer capacity;
    private SessionStatus status = SessionStatus.PREPARE;
    private List<NsUser> learners = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, 0L, 0, createdAt);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        Long price, int capacity, LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, price, capacity, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage,
        Long price, Integer capacity, LocalDateTime createdAt) {

        this(id, startDate, endDate, coverImage, price, capacity, SessionStatus.PREPARE, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        Long price, Integer capacity, SessionStatus status, LocalDateTime createdAt) {
        validateDate(startDate, endDate);
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.price = price;
        this.capacity = capacity;
        this.status = status;
        this.createdAt = createdAt;
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidSessionException("강의 종료일이 시작일보다 앞섭니다");
        }
    }

    public void join(NsUser learner, Payment payment) {
        validateJoinable();
        validatePaid(learner, payment);
        learners.add(learner);
    }

    private void validateJoinable() {
        boolean exceedCapacity = isPaidSession() && capacity < learners.size() + 1;
        if (exceedCapacity) {
            throw new CanNotJoinSessionException("모집 정원을 초과했습니다");
        }
        if (status != RECRUIT) {
            throw new CanNotJoinSessionException("모집중 상태가 아닙니다");
        }
    }

    private void validatePaid(NsUser learner, Payment payment) {
        if (!isPaidSession()) {
            return;
        }
        validatePayment(learner, payment);
        validatePayEnough(payment);
    }

    private void validatePayEnough(Payment payment) {
        boolean paidNotEnough = payment.getAmount() < price;
        if (paidNotEnough) {
            throw new CanNotJoinSessionException("결제 금액이 부족합니다");
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

    public boolean isPaidSession() {
        return price > 0;
    }

    public List<NsUser> getLearners() {
        return learners;
    }

    public Long getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }
}
