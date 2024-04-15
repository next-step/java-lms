package nextstep.courses.domain;

import nextstep.courses.code.ChargeType;
import nextstep.courses.domain.vo.Charge;
import nextstep.courses.domain.vo.Enrollment;
import nextstep.courses.domain.vo.SessionInfo;
import nextstep.courses.domain.vo.SessionPeriod;
import nextstep.courses.exception.AlreadyEnrolledException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Session {

    private Long id;

    private Course course;

    private SessionInfo info;

    private SessionPeriod period;

    private SessionImage coverImage;

    private Enrollment enrollment;

    private Charge charge;

    private BaseInfo baseInfo;

    public Session() {
    }

    public Session(Course course,
                   SessionInfo info,
                   SessionPeriod period,
                   SessionImage coverImage,
                   Enrollment enrollment,
                   Charge charge,
                   Long creatorId) {
        this(null,
                course,
                info,
                period,
                coverImage,
                enrollment,
                charge,
                new BaseInfo(creatorId));
    }

    public Session(Long id,
                   Course course,
                   SessionInfo info,
                   SessionPeriod period,
                   SessionImage coverImage,
                   Enrollment enrollment,
                   Charge charge,
                   BaseInfo baseInfo) {
        validateSessionCharge(charge, enrollment);

        this.id = id;
        this.course = course;
        this.info = info;
        this.period = period;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
        this.charge = charge;
        this.baseInfo = baseInfo;
    }

    public void toCourse(Course course) {
        this.course = course;
    }

    public void enroll(NsUser student, Payment payment) throws AlreadyEnrolledException {
        validateSessionPayment(payment.amount());
        enrollment.enroll(student);
    }

    public Long id() {
        return id;
    }

    public Enrollment enrollment() {
        return enrollment;
    }


    private void validateSessionCharge(Charge charge, Enrollment enrollment) {
        if (charge.type().equals(ChargeType.FREE) && enrollment.capacity() != 0) {
            throw new IllegalArgumentException("무료 강의는 수강인원 제한이 없어야 합니다.");
        }
        if (charge.type().equals(ChargeType.PAID) && enrollment.capacity() == 0) {
            throw new IllegalArgumentException("유료 강의는 수강인원 제한이 있어야 합니다.");
        }
    }

    private void validateSessionPayment(Long paymentAmount) {
        if (!Objects.equals(charge.price(), paymentAmount)) {
            throw new IllegalArgumentException("강의가격과 결제금액이 일치하지 않습니다.");
        }
    }

}
