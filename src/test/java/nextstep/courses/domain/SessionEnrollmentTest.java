package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.domain.session.SessionEnrollment;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.domain.session.condition.SessionConditions;
import nextstep.courses.domain.session.condition.creator.CostSessionConditionsCreator;
import nextstep.courses.domain.session.condition.creator.FreeSessionConditionsCreator;
import nextstep.courses.domain.session.condition.creator.SessionConditionsCreator;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.NsUserFixture.nsUser;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.MAX_CAPACITY;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.sessionEnrollment;
import static nextstep.courses.domain.fixture.SessionFeeFixture.SESSION_FEE;
import static nextstep.courses.domain.fixture.SessionFeeFixture.sessionFee;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionEnrollmentTest {

    @Test
    @DisplayName("[성공] 무료 강의를 수강신청 한다.")
    void 무료_강의_수강신청() {
        NsUser user = nsUser();

        SessionConditionsCreator conditionsCreator = new FreeSessionConditionsCreator();
        SessionConditions conditions = new SessionConditions(conditionsCreator);
        SessionEnrollment enrollment = sessionEnrollment(conditions);
        assertThatNoException().isThrownBy(() -> enrollment.enroll(user));
    }

    @Test
    @DisplayName("[성공] 유료 강의를 수강신청 한다.")
    void 유료_강의_수강신청() {
        SessionCapacity capacity = sessionCapacity(MAX_CAPACITY);
        SessionFee fee = sessionFee(SESSION_FEE);

        SessionConditionsCreator conditionsCreator = new CostSessionConditionsCreator(fee, SESSION_FEE, capacity);
        SessionConditions conditions = new SessionConditions(conditionsCreator);

        SessionEnrollment enrollment = sessionEnrollment(capacity, fee, conditions);
        NsUser user = nsUser();

        assertThatNoException().isThrownBy(() -> enrollment.enroll(user));
    }

}
