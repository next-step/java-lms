package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatCode;

public class FreeSessionTest {

    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    @Test
    void test01() {
        FreeSession session = new FreeSession(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        assertThatCode(() -> {
            for (int i = 0; i < 1_000; i++) {
                NsUser user = new NsUser((long) i, String.valueOf(i), "password", "name", "test@email.com");
                session.register(user);
            }
        }).doesNotThrowAnyException();
    }
}
