package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class InstructorTest {

    @Test
    @DisplayName("수강 승인_선발된 인원이 아닐 경우")
    void approve() {
        assertThatThrownBy(() -> {
//            instrutor.approve(student);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("선발된 인원이 아닙니다.");


    }
}
