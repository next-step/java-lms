package nextstep.courses.domain.session;

import nextstep.courses.exception.NotRecruitingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.Status.*;
import static org.assertj.core.api.Assertions.*;

public class StatusTest {

    @DisplayName("인자로 강의 상태를 전달 받아 모집중이 아니면 예외를 발생시킨다.")
    @Test
    void validateStatus() {
        assertThatThrownBy(() -> Status.validate(PREPARE)).isInstanceOf(NotRecruitingException.class)
            .hasMessage("해당 강의의 현재 상태는 준비중입니다.");
    }
}
