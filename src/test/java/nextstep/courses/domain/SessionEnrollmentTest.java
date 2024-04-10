package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.Students;
import nextstep.courses.domain.session.engine.SessionEnrollment;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.PaymentFixture.payment;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.costSessionEnrollment;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.freeSessionEnrollment;
import static nextstep.courses.domain.fixture.SessionFeeFixture.sessionFee;
import static nextstep.courses.domain.fixture.StudentFixture.students;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionEnrollmentTest {

    @Test
    @DisplayName("[성공] 무료 강의 조건을 만족한다.")
    void 무료_강의_수강신청() {
        SessionStatus status = SessionStatus.RECRUITING;
        SessionCapacity capacity = sessionCapacity(10);
        SessionFee sessionFee = sessionFee(800_000L);
        SessionEnrollment enrollment = freeSessionEnrollment(status, capacity, sessionFee);

        Students students = students();
        Payment payment = payment(0L);

        assertThatNoException().isThrownBy(() -> enrollment.satisfy(students, payment));
    }

    @Test
    @DisplayName("[성공] 유료 강의를 수강신청 한다.")
    void 유료_강의_수강신청() {
        SessionStatus status = SessionStatus.RECRUITING;
        SessionCapacity capacity = sessionCapacity(10);
        SessionFee sessionFee = sessionFee(800_000L);
        SessionEnrollment enrollment = costSessionEnrollment(status, capacity, sessionFee);

        Students students = students();
        Payment payment = payment(800_000L);

        assertThatNoException().isThrownBy(() -> enrollment.satisfy(students, payment));
    }

}
