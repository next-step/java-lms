package nextstep.users.domain;

import nextstep.tutor.domain.NsTutor;
import nextstep.tutor.exception.TutorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NsTutorTest {
    public static final NsTutor TESTTRACE = new NsTutor(1L, "testtrace", "password", "name", "testtrace@slipp.net");

    @Test
    @DisplayName("실패 - 강사가 일치 하지 않을 경우 예외가 발생한다.")
    void fail_session_approve_not_equal_tutor() {
        assertThatThrownBy(() -> TESTTRACE.isSameTutor(2L))
                .isInstanceOf(TutorException.class)
                .hasMessage("강사가 일치 하지 않습니다.");
    }

}
