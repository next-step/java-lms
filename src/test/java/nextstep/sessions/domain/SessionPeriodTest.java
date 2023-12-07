package nextstep.sessions.domain;

import nextstep.images.domain.Image;
import nextstep.images.domain.ImageType;
import nextstep.sessions.strategy.EnrollmentStrategy;
import nextstep.sessions.strategy.FreeEnrollment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {

    @Test
    @DisplayName("강의 시작일은 종료일을 넘을 수 없다.")
    void test1() {
        LocalDateTime startedDate = LocalDateTime.of(2023, 12, 30, 0, 0, 0);
        LocalDateTime endedDate = LocalDateTime.of(2023, 12, 1, 0, 0, 0);

        assertThatThrownBy(() -> new SessionPeriod(startedDate, endedDate))
                .hasMessageContaining("강의 시작일은 종료일을 넘을 수 없습니다.");
    }

}