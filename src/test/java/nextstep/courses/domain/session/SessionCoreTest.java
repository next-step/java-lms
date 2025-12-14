package nextstep.courses.domain.session;

import nextstep.courses.domain.session.builder.EnrollmentBuilder;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.courses.domain.session.constant.SessionRecruitmentStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionCoreTest {


    @Test
    void 강의신청시_비모집상태_에러발생(){
        Session session = new SessionBuilder().withRecruit(SessionRecruitmentStatus.NOT_RECRUITING).build();
        Enrollment enrollment = new EnrollmentBuilder().build();
        EnrollmentApply enrollmentApply = new EnrollmentApply(new Enrollments(enrollment), new Payment(1L, 1L, 300_000L), session.getSessionCore());

        assertThatThrownBy(() -> enrollmentApply.enroll(enrollment.getUser(), session.getId()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의신청시_모집상태_정상_신청(){
        Session session = new SessionBuilder().withRecruit(SessionRecruitmentStatus.RECRUITING).build();
        Enrollment enrollment = new EnrollmentBuilder().build();
        EnrollmentApply enrollmentApply = new EnrollmentApply(new Enrollments(), new Payment(1L, 1L, 300_000L), session.getSessionCore());
        enrollmentApply.enroll(enrollment.getUser(), session.getId());

        assertThat(enrollmentApply.getEnrollments()).hasSize(1);
    }
}