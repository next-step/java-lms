package nextstep.courses.domain;

import nextstep.courses.exception.SessionCapacityExceedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.SessionCapacityFixture.MAX_CAPACITY;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionCapacityTest {

    @Test
    @DisplayName("[성공] 수강 인원을 생성한다.")
    void 수강_인원생성() {
        assertThatNoException()
                .isThrownBy(() -> sessionCapacity(MAX_CAPACITY));
    }

    @Test
    @DisplayName("[실패] 최소 인원 미만의 수강 인원을 생성할 경우 InvalidSessionCapacityException 예외가 발생한다.")
    void 최대_인원_초과_생성() {
        assertThatExceptionOfType(SessionCapacityExceedException.class)
                .isThrownBy(() -> sessionCapacity(0));
    }

}
