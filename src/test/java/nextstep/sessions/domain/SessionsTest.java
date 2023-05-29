package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionsTest {
    @DisplayName("Sessions 객체가 잘 생성되는지 확인")
    @Test
    void Sessions_객체가_정상적으로_생성되는지_확인() {
        assertThat(Sessions.create()).isInstanceOf(Sessions.class);
    }

    @DisplayName("Session 객체가 정상적으로 추가되는지 확인")
    @Test
    void Session_객체가_정상적으로_추가되는지_확인() {
        //given
        Sessions sessions = Sessions.create();

        //when
        sessions.add(SessionTest.S1);
        sessions.add(SessionTest.S2);

        //then
        assertThat(sessions.getSessions()).containsExactly(SessionTest.S1, SessionTest.S2);
    }

}
