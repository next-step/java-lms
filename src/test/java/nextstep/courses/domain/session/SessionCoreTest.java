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
        assertThatThrownBy(() -> session.addEnrollment(new EnrollmentBuilder().build(), new Payment(1L, 1L, 300_000L)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의신청시_모집상태_정상_신청(){
        Session session = new SessionBuilder().withRecruit(SessionRecruitmentStatus.RECRUITING).build();
        session.addEnrollment(new EnrollmentBuilder().build(), new Payment(1L, 1L, 300_000L));
        assertThat(session.getEnrollments()).hasSize(1);
    }
}