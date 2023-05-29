package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.SessionTerm;
import nextstep.courses.exceptions.InvalidTermsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SessionTermTest {

    @Test
    void 종료일이_시작일보다_이전() {
        LocalDateTime endAt = LocalDateTime.now();
        LocalDateTime startAt = endAt.plusDays(1);

        Assertions.assertThatThrownBy(() -> new SessionTerm(startAt, endAt))
                .isInstanceOf(InvalidTermsException.class)
                .hasMessage("수강기간은 종료일이 시작일보다 이후여야 합니다.");
    }

    @Test
    void 시작일과_종료일이_같음() {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt;

        Assertions.assertThatThrownBy(() -> new SessionTerm(startAt, endAt))
                .isInstanceOf(InvalidTermsException.class)
                .hasMessage("수강기간은 종료일이 시작일보다 이후여야 합니다.");
    }

    @Test
    void 정상_등록() {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusDays(1);

        SessionTerm sessionTerm = new SessionTerm(startAt, endAt);

        Assertions.assertThat(sessionTerm).isNotNull();
    }
}
