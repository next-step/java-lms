package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void 무료_강의_수강신청() throws ExceedSessionCapacityException {
        NsUser user = nsUser();

        SessionEnrollmentCondition noneCondition = new SessionNoneCondition();
        SessionEnrollment enrollment = sessionEnrollment(SessionEnrollmentConditions.from(noneCondition));
        assertThatNoException().isThrownBy(() -> enrollment.enroll(user));
    }

    @Test
    @DisplayName("[성공] 유료 강의를 수강신청 한다.")
    void 유료_강의_수강신청() throws ExceedSessionCapacityException {
        SessionCapacity capacity = sessionCapacity(MAX_CAPACITY);
        SessionFee fee = sessionFee(SESSION_FEE);

        SessionEnrollmentCondition capacityCondition = new SessionCapacityCondition(capacity);
        SessionEnrollmentCondition feeCondition = new SessionFeeCondition(fee, SESSION_FEE);
        SessionEnrollmentConditions conditions = SessionEnrollmentConditions.of(List.of(capacityCondition, feeCondition));

        SessionEnrollment enrollment = sessionEnrollment(capacity, fee, conditions);
        NsUser user = nsUser();

        assertThatNoException().isThrownBy(() -> enrollment.enroll(user));
    }

}
