package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

public class PaidEnrollmentRuleTest {

    @Test
    void 유료_강의_등록_성공() {
        PaidEnrollmentRule paidEnrollmentRule = new PaidEnrollmentRule(50000, 5);

        paidEnrollmentRule.validate(new Money(50000),1);
    }
}
