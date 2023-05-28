package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionsTest {
    
    @Test 
    public void add() throws Exception {
        Sessions sessions = new Sessions();
        sessions.addSession(SessionTest.SESSION01);
        assertThat(sessions.size()).isEqualTo(1);
    }
}
