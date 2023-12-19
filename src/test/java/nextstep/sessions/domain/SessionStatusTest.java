package nextstep.sessions.domain;

import nextstep.common.Period;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {

    @DisplayName("강의 기간 정보를 전달하면 SessionStatus 객체를 생성한다.")
    @Test
    void sessionStatusTest() {
        assertThat(new SessionStatus(new Period(LocalDate.of(2023, 12, 15), LocalDate.of(2023, 12, 31)))).isInstanceOf(SessionStatus.class);
    }

    @DisplayName("모집중이지 않은 강의는 true를 반환한다.")
    @Test
    void checkNonRecruitTest() {
        SessionStatus sessionStatus = new SessionStatus(new Period(LocalDate.of(2023, 11, 01), LocalDate.of(2023, 11, 15)));

        assertThat(sessionStatus.isNotRecruiting()).isTrue();
    }

    @DisplayName("모집중인 강의는 false를 반환한다.")
    @Test
    void checkRecruitTest() {
        SessionStatus sessionStatus1 = new SessionStatus(new Period(LocalDate.of(2023, 12, 01), LocalDate.of(2023, 12, 31)));
        SessionStatus sessionStatus2 = new SessionStatus(new Period(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 15)));

        assertThat(sessionStatus1.isNotRecruiting()).isFalse();
        assertThat(sessionStatus2.isNotRecruiting()).isFalse();
    }
}
