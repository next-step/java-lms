package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다.")
    @Test
    void test01() {
        Session session = new PaidSession(1);
        assertThatThrownBy(() -> session.register(List.of(NsUserTest.JAVAJIGI)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강인원을 초과하였습니다.");
    }
}
