package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

    static final String ERROR_INVALID_DATE = "시작일이 종료일보다 빨라야 합니다";

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final SessionStatus status;

    private final boolean isPaid;

    private final Integer maxCapacity;

    private final int fee;

    public Session(LocalDate startDate, LocalDate endDate) {
        this(startDate, endDate, false, null, 0);
    }

    public Session(LocalDate startDate, LocalDate endDate, boolean isPaid, Integer maxCapacity) {
        this(startDate, endDate, isPaid, maxCapacity, 0);
    }

    public Session(LocalDate startDate, LocalDate endDate, boolean isPaid, Integer maxCapacity, int fee) {
        validateDate(startDate, endDate);
        validateCapacity(isPaid, maxCapacity);
        validateFee(isPaid, fee);
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = SessionStatus.PREPARING;
        this.isPaid = isPaid;
        this.maxCapacity = maxCapacity;
        this.fee = fee;
    }

    public SessionStatus status() {
        return status;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Integer maxCapacity() {
        return maxCapacity;
    }

    public int fee() {
        return fee;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
        }
    }

    private void validateCapacity(boolean isPaid, Integer maxCapacity) {
        if (isPaid && (maxCapacity == null || maxCapacity <= 0)) {
            throw new IllegalArgumentException("유료 강의는 최대 수강인원이 있어야 합니다");
        }
        if (!isPaid && maxCapacity != null) {
            throw new IllegalArgumentException("무료 강의는 최대 수강인원이 없어야 합니다");
        }
    }

    private void validateFee(boolean isPaid, int fee) {
        if (isPaid && fee <= 0) {
            throw new IllegalArgumentException("유료 강의는 0원 초과 여야 합니다");
        }
        if (!isPaid && fee != 0) {
            throw new IllegalArgumentException("무료 강의는 0원 이어야 합니다");
        }
    }

}
