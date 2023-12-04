package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionStatusEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionEnrollmentManagementTest {
    @DisplayName("강의 상태가 모집중이 아닐경우 예외발생")
    @Test
    void 강의_상태_확인() {
        SessionEnrollmentManagement completedSession = new SessionEnrollmentManagement(
                new PricingPolicy(PricingTypeEnum.FREE, 0),
                SessionStatusEnum.COMPLETED, Integer.MAX_VALUE);
        assertThatThrownBy(() -> completedSession.sessionStatusCheck())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("모집중이 아닙니다.");
    }
}