package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import nextstep.courses.exception.SessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.fixture.SessionFixture.payment;
import static nextstep.courses.domain.fixture.SessionFixture.session;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @Test
    @DisplayName("[성공] 무료 강의를 수강신청 한다.")
    void 무료_강의_수강신청() throws SessionException {
        Session session = session(EnrollmentConditions.from(new NoneCondition()));
        Session sessionAfterEnroll = session.enroll();
        assertThat(sessionAfterEnroll.getCapacity().getCurrentCapacity())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("[성공] 유료 강의를 수강신청 한다.")
    void 유료_강의_수강신청() throws SessionException {
        Session session = session(EnrollmentConditions.of(
                List.of(new CapacityCondition(), new FeeCondition(payment())))
        );
        Session sessionAfterEnroll = session.enroll();
        assertThat(sessionAfterEnroll.getCapacity().getCurrentCapacity())
                .isEqualTo(1);
    }

}
