package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.SessionTest.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentConditionTest {
    public static final EnrollmentCondition JAVAJIGI_CONDITION = new EnrollmentCondition(NsUserTest.JAVAJIGI, new Payment(SESSION_ID, NsUserTest.JAVAJIGI.getId(), 30_000L));
    public static final EnrollmentCondition SANJIGI_CONDITION = new EnrollmentCondition(NsUserTest.SANJIGI, new Payment(SESSION_ID, NsUserTest.SANJIGI.getId(), 30_000L));

    @Test
    void 수강신청자와_결제자가_서로_다르면_수강신청_조건을_충족하지_않는다() {
        assertThatThrownBy(() -> new EnrollmentCondition(NsUserTest.JAVAJIGI, new Payment(SESSION_ID, NsUserTest.SANJIGI.getId(), 30_000L)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}