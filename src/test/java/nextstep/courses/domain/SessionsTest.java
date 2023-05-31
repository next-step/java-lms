package nextstep.courses.domain;

import static nextstep.courses.domain.SessionTest.SESSION1;
import static nextstep.courses.domain.SessionTest.SESSION2;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SessionsTest {

    @Test
    void create() {
        List<Session> sessionList = List.of(SESSION1, SESSION2);
        Sessions sessions = new Sessions(sessionList);

        assertThat(sessions.getSession())
                .isEqualTo(List.of(SESSION1, SESSION2));
    }

    @Test
    void addSession() {
      List<Session> sessionList = new ArrayList<>(List.of(SESSION1));
      Sessions sessions = new Sessions(sessionList);

      sessions.add(SESSION2);

      assertThat(sessions.getSession())
              .isEqualTo(List.of(SESSION1, SESSION2));
    }
}
