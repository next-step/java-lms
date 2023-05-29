package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionProgressStatusTest {
    @Test
    @DisplayName("강의 진행 상태가 진행중인 경우 수강신청 가능하다")
    void progress_status() {
        SessionProgressStatus progressing = SessionProgressStatus.PROGRESSING;
        assertThat(progressing.canEnroll()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("강의 진행 상태가 준비중, 종료인 경우 신청할 수 없다")
    @EnumSource(names = {"PREPARING", "END"})
    void not_progress_status(SessionProgressStatus sessionProgressStatus) {
        assertThat(sessionProgressStatus.canEnroll()).isFalse();
    }
}
