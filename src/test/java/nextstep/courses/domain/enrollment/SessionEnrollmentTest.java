//package nextstep.courses.domain.enrollment;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import nextstep.courses.domain.session.engine.Session;
//import nextstep.courses.domain.session.enrollment.Enrollment;
//import nextstep.courses.domain.session.enrollment.SessionEnrollment;
//import nextstep.courses.domain.session.enrollment.state.RecruitmentState;
//import nextstep.courses.domain.session.enrollment.state.SessionState;
//import nextstep.courses.fixture.builder.FreeSessionBuilder;
//import nextstep.courses.fixture.builder.PaidSessionBuilder;
//import nextstep.payments.domain.Money;
//import nextstep.payments.domain.Payment;
//import org.junit.jupiter.api.Test;
//
//class SessionEnrollmentTest {
//
//    @Test
//    void 무료강의는_수강신청_등록이_되어야_한다(){
//
//        Enrollment enrollment = new SessionEnrollment(1), new SessionState(RecruitmentState.RECRUITING), new Money(0));
//        Payment afterPayment = new Payment("1L", 1L, 1L, new Money(50000));
//        Session session = FreeSessionBuilder.anFreeSession()
//            .withName("강의이름")
//            .withSessionStatus(RecruitmentState.RECRUITING)
//            .build();
//
//        enrollment.enroll(session, afterPayment);
//
//        assertThat(session.getRegistrationCount()).isEqualTo(1);
//    }
//
//    @Test
//    void 유료강의는_수강신청_등록이_되어야_한다(){
//
//        Enrollment enrollment = new SessionEnrollment(new StudentName("김남협"), 1L, new Money(50000));
//        Payment afterPayment = new Payment("1L", 1L, 1L, new Money(50000));
//        Session session = PaidSessionBuilder.anFreeSession()
//            .withName("강의이름")
//            .withMaxRegistrationCount(3)
//            .withTuitionFee(50000)
//            .withSessionStatus(RecruitmentState.RECRUITING)
//            .build();
//
//        enrollment.enroll(session, afterPayment);
//
//        assertThat(session.getRegistrationCount()).isEqualTo(1);
//    }
//}
