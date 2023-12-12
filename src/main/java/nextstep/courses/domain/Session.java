package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.domain.dto.SessionEnrollment;
import nextstep.courses.domain.dto.SessionPayment;

public class Session {
    private Long id;
    private SessionStatus sessionStatus;
    private SessionPaymentType sessionPaymentType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long price;
    private Integer countOfEnrollments;
    private Integer limitOfEnrollments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.id = 0L;
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionPaymentType = SessionPaymentType.FREE;
        this.price = 0L;
        this.countOfEnrollments = 0;
        this.limitOfEnrollments = 0;
        this.sessionStatus = SessionStatus.READY;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Session(Long id, SessionPayment sessionPayment, SessionEnrollment sessionEnrollment, LocalDateTime startDate,
                   LocalDateTime endDate) {
        this.id = id;
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        validatePrice(sessionPayment.getType(), sessionPayment.getAmount());
        this.sessionPaymentType = sessionPayment.getType();
        this.price = sessionPayment.getAmount();
        validateCountOfEnrollments(sessionPayment, sessionEnrollment);
        this.countOfEnrollments = sessionEnrollment.getCountOfEnrollments();
        this.limitOfEnrollments = sessionEnrollment.getLimits();
        this.sessionStatus = SessionStatus.READY;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException();
        }
    }

    private void validatePrice(SessionPaymentType sessionPaymentType, Long price) {
        if (sessionPaymentType == SessionPaymentType.PAID && price <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCountOfEnrollments(SessionPayment sessionPayment, SessionEnrollment sessionEnrollment) {
        if (!sessionPayment.isPaid() && sessionEnrollment.isNotEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
