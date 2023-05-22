package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("강의는 시작일을 가진다.")
    void startAt() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        Session session = new Session(startAt, endAt);
        assertThat(session.getStartAt()).isEqualTo(startAt);
    }

    @Test
    @DisplayName("강의는 종료일을 가진다.")
    void endAt() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        Session session = new Session(startAt, endAt);
        assertThat(session.getEndAt()).isEqualTo(endAt);
    }


}
