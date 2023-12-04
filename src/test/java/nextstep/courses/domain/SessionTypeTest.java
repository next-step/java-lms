package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTypeTest {

    @DisplayName("무료 강의는 누구나 수강신청이 가능합니다.")
    @Test
    void a() {
        FreeSessionType freeSessionType = new FreeSessionType();
        assertThatNoException().isThrownBy(freeSessionType::registered);
    }

    @DisplayName("2명이 최대 정원인 유료 강의는 최대 수강 인원을 초과할 수 없습니다.")
    @Test
    void b() {
        long pirce = 10_000;
        int maximumHeadCount = 100;
        PaidSessionType paidSessionType = new PaidSessionType(maximumHeadCount, pirce);

        assertThatNoException().isThrownBy(() -> paidSessionType.registered(pirce, 99));
        assertThatIllegalArgumentException().isThrownBy(() -> paidSessionType.registered(pirce, 100));
        assertThatIllegalArgumentException().isThrownBy(() -> paidSessionType.registered(pirce, 101));
    }

    @DisplayName("유료 강의는 결제금이 강의료와 같아야 수강신청이 가능합니다.")
    @Test
    void c() {
        long pirce = 10_000;
        int maximumHeadCount = 2;
        PaidSessionType paidSessionType = new PaidSessionType(maximumHeadCount, pirce);

        assertThatNoException().isThrownBy(() -> paidSessionType.registered(pirce, 1));
        assertThatIllegalArgumentException().isThrownBy(() -> paidSessionType.registered(1000, 1));
    }


}
