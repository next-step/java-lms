package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다.")
    @Test
    void test01() {
        Session session = new PaidSession(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        assertThatThrownBy(() -> session.register(List.of(NsUserTest.JAVAJIGI)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강인원을 초과하였습니다.");
    }

    @DisplayName("수강 신청은 강의 상태가 모집 중일 때만 가능하다.")
    @Test
    void test02() {
        Session session = new PaidSession(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        session.changeStatus(SessionStatus.RECRUITING);
        assertThatCode(() -> session.register(List.of(NsUserTest.JAVAJIGI))).doesNotThrowAnyException();
    }
}
