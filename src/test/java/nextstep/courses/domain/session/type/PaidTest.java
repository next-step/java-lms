package nextstep.courses.domain.session.type;

import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.EnrollmentConditionTest.SESSION_ID;
import static nextstep.courses.domain.session.EnrollmentConditionTest.JAVAJIGI_ENROLLMENT;
import static org.assertj.core.api.Assertions.assertThat;

class PaidTest {
    @Test
    void 유료_강의는_수강생이_결제한_금액과_수강료가_일치할_때_수강_신청이_가능하다() {
        Paid paidSession = new Paid(SESSION_ID, 30_000L);
        assertThat(paidSession.canEnroll(JAVAJIGI_ENROLLMENT)).isTrue();
    }

    @Test
    void 유료_강의는_수강생이_결제한_금액과_수강료가_불일치하면_수강_신청이_불가하다() {
        Paid paidSession = new Paid(SESSION_ID, 20_000L);
        assertThat(paidSession.canEnroll(JAVAJIGI_ENROLLMENT)).isFalse();
    }

    @Test
    void 유료_강의는_수강생이_결제한_강의와_신청한_강의가_불일치하면_수강_신청이_불가하다() {
        Paid paidSession = new Paid(SESSION_ID + 1, 20_000L);
        assertThat(paidSession.canEnroll(JAVAJIGI_ENROLLMENT)).isFalse();
    }
}