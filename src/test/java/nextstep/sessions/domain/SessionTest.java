package nextstep.sessions.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static nextstep.sessions.domain.SessionState.PREPARING;
import static nextstep.sessions.domain.SessionState.RECRUITING;
import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    void create() {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session("TDD, 자바 18기", PREPARING, now);
        assertThat(session).isEqualTo(new Session("TDD, 자바 18기", PREPARING, now));
    }

    @Test
    void 강의상태가_모집중인경우_등록가능하다() {
        Session session = new Session("TDD, 자바 18기", RECRUITING);
        assertThat(session.isPossibleRegistration()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RECRUITING"})
    void 강의상태가_모집중이_아닌경우_등록불가하다(SessionState sessionState) {
        Session session = new Session("TDD, 자바 18기", sessionState);
        assertThat(session.isPossibleRegistration()).isFalse();
    }
}
