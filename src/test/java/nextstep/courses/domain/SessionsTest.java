package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionsTest {

    @DisplayName("강의 신청 시 해당하는 강의 id 가 없을 경우에 에러를 응답한다.")
    @Test
    public void 강의_신청_해당_강의_없는경우_테스트() {
        Session session = new Session(1L, LocalDateTime.now(), null,
                new SessionRegistration(10, 0, SessionStatusType.RECRUITING, new ArrayList<>()),
                new CoverImage("url"),
                new SessionDate(LocalDateTime.now(), LocalDateTime.now()),
                new Price(1000));

        Session session2 = new Session(2L, LocalDateTime.now(), null,
                new SessionRegistration(10, 5, SessionStatusType.RECRUITING, new ArrayList<>()),
                new CoverImage("url2"),
                new SessionDate(LocalDateTime.now(), LocalDateTime.now()),
                new Price(0));

        Sessions sessions = new Sessions(List.of(session, session2));

        assertThatThrownBy(() -> sessions.registerSession(3L, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
