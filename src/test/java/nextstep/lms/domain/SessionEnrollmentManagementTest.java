package nextstep.lms.domain;

import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionRecruitmentEnum;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionEnrollmentManagementTest {
    @DisplayName("강의 모집상태가 모집중이 아닐경우 수강생 등록 시 예외발생")
    @Test
    void 강의_상태_확인() {
        SessionEnrollmentManagement completedSession = new SessionEnrollmentManagement(
                new PricingPolicy(PricingTypeEnum.FREE, 0L),
                SessionRecruitmentEnum.NON_RECRUITMENT, Integer.MAX_VALUE);
        final Students students = new Students(Collections.emptyList());
        final Payment payment = new Payment("1", 1L, 1L, 0L);

        assertThatIllegalArgumentException().isThrownBy(() -> completedSession.enrollableCheck(students, payment))
                .withMessage("모집중이 아닙니다.");
    }
}