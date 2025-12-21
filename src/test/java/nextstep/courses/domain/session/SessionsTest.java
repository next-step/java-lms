package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.policy.FreeSessionPolicy;
import org.junit.jupiter.api.Test;

public class SessionsTest {

    @Test
    void 이미_추가된_강의라면_예외가_발생한다() {
        Sessions sessions = new Sessions();
        Session session = new Session(
                new SessionPeriod(LocalDate.now(), LocalDate.now().plusMonths(3)),
                new CoverImage("파일이름.png", 1024, 1500, 1000),
                new FreeSessionPolicy()
        );

        sessions.add(session);

        assertThatThrownBy(() -> sessions.add(session))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("이미 포함된 강의입니다.");
    }
}
