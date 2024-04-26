package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.count.FreeEnrollmentCount;
import nextstep.courses.domain.session.enrollment.count.MaxRegistrationCount;
import nextstep.courses.domain.session.enrollment.count.PaidEnrollmentCount;
import nextstep.courses.domain.session.enrollment.count.RegistrationCount;
import nextstep.courses.domain.session.enrollment.state.RecruitmentState;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.domain.student.Student;
import nextstep.courses.error.exception.MaxRegistrationExceededException;
import nextstep.courses.error.exception.PaymentConditionNotMetException;
import nextstep.courses.error.exception.SessionNotOpenForEnrollmentException;
import nextstep.courses.fixture.builder.EnrollmentBuilder;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    private NsUser nsUser;

    @BeforeEach
    void setUp() {
        nsUser = new NsUser(1L, "namhyeop", "1234", "kimnamhyoep", "nam@gmail.com");
    }

    @Test
    void 무료강의는_수강신청_등록이_되어야_한다() {
        Enrollment enrollment = EnrollmentBuilder.anEnrollment()
            .withEnrollmentCount(new FreeEnrollmentCount(new RegistrationCount(1)))
            .withSessionState(new SessionState(RecruitmentState.RECRUITING))
            .withTuitionFee(new Money(0))
            .withFeeType(FeeType.FREE)
            .build();

        Payment payment = new Payment("1", 1L, 1L, new Money(0));

        Student student = enrollment.enroll(nsUser, payment);

        assertThat(student).extracting("studentName", "email", "paymentAmount")
            .containsExactly("kimnamhyoep", "nam@gmail.com", 0);
    }

    @Test
    void 유료강의는_수강신청_등록이_되어야_한다() {
        Enrollment enrollment = EnrollmentBuilder.anEnrollment()
            .withEnrollmentCount(
                new PaidEnrollmentCount(new RegistrationCount(1), new MaxRegistrationCount(5)))
            .withSessionState(new SessionState(RecruitmentState.RECRUITING))
            .withTuitionFee(new Money(50000))
            .withFeeType(FeeType.PAID)
            .build();

        Payment payment = new Payment("1", 1L, 1L, new Money(50000));

        Student student = enrollment.enroll(nsUser, payment);

        assertThat(student).extracting("studentName", "email", "paymentAmount")
            .containsExactly("kimnamhyoep", "nam@gmail.com", 50000);
    }

    @Test
    void 수강신청시_결제금액과_수강비용이_일치하지_않은_경우_예외가_발생한다() {
        Enrollment enrollment = EnrollmentBuilder.anEnrollment()
            .withEnrollmentCount(new FreeEnrollmentCount(new RegistrationCount(1)))
            .withSessionState(new SessionState(RecruitmentState.RECRUITING))
            .withTuitionFee(new Money(0))
            .withFeeType(FeeType.FREE)
            .build();

        Payment payment = new Payment("1", 1L, 1L, new Money(50000));

        assertThatThrownBy(() -> enrollment.enroll(nsUser, payment))
            .isInstanceOf(PaymentConditionNotMetException.class)
            .hasMessage("결제 금액이 일치하지 않아 강의 등록에 실패하였습니다 강의료: 0, 결제금액: 50,000");
    }

    @Test
    void 수강신청시_강의가_모집중이지_않은_경우에는_예외가_발생한다() {
        Enrollment enrollment = EnrollmentBuilder.anEnrollment()
            .withEnrollmentCount(new FreeEnrollmentCount(new RegistrationCount(1)))
            .withSessionState(new SessionState(RecruitmentState.PREPARING))
            .withTuitionFee(new Money(0))
            .withFeeType(FeeType.FREE)
            .build();

        Payment payment = new Payment("1", 1L, 1L, new Money(0));

        assertThatThrownBy(() -> enrollment.enroll(nsUser, payment))
            .isInstanceOf(SessionNotOpenForEnrollmentException.class)
            .hasMessage("강의는 모집중 상태에서만 등록 가능합니다 현재 강의 상태: 준비중");
    }

    @Test
    void 수강신청시_강의가_강의_등록_가능_인원이_최대_등록_인원_수를_초과한_경우_예외가_발생한다() {
        Enrollment enrollment = EnrollmentBuilder.anEnrollment()
            .withEnrollmentCount(new PaidEnrollmentCount(new RegistrationCount(10), new MaxRegistrationCount(1)))
            .withSessionState(new SessionState(RecruitmentState.RECRUITING))
            .withTuitionFee(new Money(0))
            .withFeeType(FeeType.PAID)
            .build();

        Payment payment = new Payment("1", 1L, 1L, new Money(0));

        assertThatThrownBy(() -> enrollment.enroll(nsUser, payment))
            .isInstanceOf(MaxRegistrationExceededException.class)
            .hasMessage("최대 등록 인원수를 초과하였습니다. 현재 강의 등록 인원: 10");
    }
}
