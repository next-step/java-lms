package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTypeTest {
    @Test
    void 생성() {
        assertThat(new SessionType(true)).isEqualTo(new SessionType(true));
    }

    @Test
    void 유료강의_수강인원제한_가능() {
        SessionType sessionType = new SessionType(false);
        sessionType.setMaxStudents(10);
        assertThat(sessionType.isEqualMaxStudents(10)).isTrue();
    }

    @Test
    void 무료강의_수강인원제한_불가() {
        SessionType sessionType = new SessionType(true);
        sessionType.setMaxStudents(10);
        assertThat(sessionType.isEqualMaxStudents(10)).isFalse();
    }
}
