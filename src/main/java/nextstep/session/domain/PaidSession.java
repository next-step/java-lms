package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PaidSession extends Session {
    private static final SessionType SESSION_TYPE = SessionType.PAID;
    private final Integer capacity;
    private final Long price;

    public PaidSession(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, Integer capacity, Long price) {
        this(null, null, null, creatorId, startDate, endDate, sessionImage, SessionStatus.PREPARING, SessionRecruitStatus.CLOSED, SESSION_TYPE, capacity, price, null, null);
    }

    public PaidSession(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionStatus sessionStatus, SessionRecruitStatus sessionRecruitStatus, SessionType sessionType, Integer capacity, Long price, List<Enrollment> enrollments, List<Admission> admissions) {
        super(id, createdAt, updatedAt, creatorId, startDate, endDate, sessionImage, sessionStatus, sessionRecruitStatus, sessionType, enrollments, admissions);
        this.capacity = capacity;
        this.price = price;
    }

    public static PaidSession create(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, Integer limitNumberOfStudents, Long price) {
        return new PaidSession(creatorId, startDate, endDate, sessionImage, limitNumberOfStudents, price);
    }

    @Override
    protected void validateCommonEnroll(NsUser nsUser) {
        validateLimitNumberOfStudents();
        validatePayment(nsUser);
    }

    private void validateLimitNumberOfStudents() {
        if (this.capacity <= enrollments.enrolledNumber()) {
            throw new IllegalStateException("수강신청 정원이 가득찼습니다.");
        }
    }

    private void validatePayment(NsUser user) {
        if (!user.getSessionPayment(this).getAmount().equals(price)) {
            throw new IllegalArgumentException("강의의 가격과 결제한 가격이 다릅니다.");
        }
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Long getPrice() {
        return price;
    }
}
