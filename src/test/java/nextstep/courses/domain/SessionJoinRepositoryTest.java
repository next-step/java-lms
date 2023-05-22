package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SessionJoinRepositoryTest {
    @Autowired
    private SessionJoinRepository sessionJoinRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("session join crud")
    void test03() {
        Session session = aSession().build();
        long sessionId = sessionRepository.save(session);
        Session savedSession = aSession().withId(sessionId).build();
        savedSession.addUser(NsUserTest.JAVAJIGI);
        savedSession.addUser(NsUserTest.SANJIGI);

        sessionJoinRepository.save(savedSession);

        List<SessionJoin> sessionJoins = sessionJoinRepository.findAllBySessionId(savedSession.getId());
        assertThat(sessionJoins).hasSize(2);
    }

}
