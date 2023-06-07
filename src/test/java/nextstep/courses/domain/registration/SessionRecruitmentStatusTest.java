package nextstep.courses.domain.registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionRecruitmentStatusTest {

    @DisplayName("모집상태 : 정의된 값이 없는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"모집, 집중, 징집중"})
    void 강의상태_없는경우(String sessionStatus) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionRecruitmentStatus.findByName(sessionStatus))
                .withMessageMatching("없는 모집상태 입니다.");
    }

    @DisplayName("모집상태가 아닌 경우")
    @Test
    void isRecruiting() {
        SessionRecruitmentStatus status = SessionRecruitmentStatus.NOT_RECRUITING;
        assertThat(status.isNotRecruiting()).isTrue();
    }
}

