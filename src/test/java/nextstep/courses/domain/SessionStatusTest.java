package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionProgressStatus;
import nextstep.courses.domain.type.SessionRecruitingStatus;
import nextstep.courses.exception.InvalidSessionStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionStatusTest {

    @Test
    @DisplayName("종료된 강의는 모집중 상태를 가질 수 없다")
    public void session_invalid_status() {
        assertThatExceptionOfType(InvalidSessionStatusException.class)
            .isThrownBy(() -> new SessionStatus(SessionProgressStatus.TERMINATE, SessionRecruitingStatus.RECRUITING))
            .withMessageMatching("유효하지 않은 강의 상태입니다.");
    }

}
