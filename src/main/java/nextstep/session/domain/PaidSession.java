package nextstep.session.domain;

import nextstep.session.domain.policy.enroll.EnrollLimitNumberOfStudentPolicy;
import nextstep.session.domain.policy.enroll.EnrollPaymentPolicy;
import nextstep.session.domain.policy.enroll.EnrollPolicy;
import nextstep.session.domain.policy.enroll.EnrollRecruitStatusPolicy;
import nextstep.session.domain.policy.enroll.EnrollStatusPolicy;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PaidSession extends Session {
    private static final SessionType SESSION_TYPE = SessionType.PAID;
    private final Integer capacity;
    private final Long price;

    private List<EnrollPolicy<? super PaidSession>> enrollPolicies = Arrays.asList(new EnrollStatusPolicy(), new EnrollRecruitStatusPolicy(), new EnrollPaymentPolicy(), new EnrollLimitNumberOfStudentPolicy());

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
    public Enrollment enroll(NsUser user) {
        this.enrollPolicies.forEach(policy -> policy.validate(this, user));
        return enrollments.add(user, this);
    }

    public boolean isFull() {
        return enrollments.enrolledNumber() >= capacity;
    }

    public void setEnrollPolicies(List<EnrollPolicy<? super PaidSession>> enrollPolicies) {
        this.enrollPolicies = enrollPolicies;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Long getPrice() {
        return price;
    }
}
