package nextstep.courses.domain.registration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SessionRecruitmentStatusTest {

    @DisplayName("존재하지 않는 모집상태일 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"모집", "비집중"})
    void findByName_InvalidValue_ThrowException(String sessionStatus) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionRecruitmentStatus.findByName(sessionStatus))
                .withMessageMatching("존재하지 않는 모집상태입니다.");
    }

    @DisplayName("모집상태일 경우를 판별할 수 있다.")
    @Test
    void isRecruiting_MatchStatus_True() {
        SessionRecruitmentStatus status = SessionRecruitmentStatus.RECRUITING;
        Assertions.assertThat(status.isRecruiting()).isTrue();
    }

    @DisplayName("비모집상태일 경우를 판별할 수 있다.")
    @Test
    void isNotRecruiting_MatchStatus_True() {
        SessionRecruitmentStatus status = SessionRecruitmentStatus.NOT_RECRUITING;
        Assertions.assertThat(status.isNotRecruiting()).isTrue();
    }

}
