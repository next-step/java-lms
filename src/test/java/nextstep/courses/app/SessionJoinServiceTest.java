package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoin;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static nextstep.fixtures.SessionFixtures.testSession1;
import static nextstep.fixtures.SessionFixtures.testSession5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SessionJoinServiceTest {
    @Autowired
    private SessionJoinService sessionJoinService;

    @Autowired
    private SessionRepository sessionRepository;


    @DisplayName("등록")
    @Test
    void enroll() {
        Session session = testSession1();
        long savedSessionId = sessionRepository.save(session);

        sessionJoinService.register(savedSessionId, NsUserTest.JAVAJIGI);

        List<SessionJoin> findSessionJoins = sessionRepository.findAllSessionJoinBySessionId(savedSessionId);
        assertThat(findSessionJoins).hasSize(1);
    }

    @DisplayName("강의 등록시 인원 수 초과")
    @Test
    void maxUserException() {
        Session session = testSession5();
        long savedSessionId = sessionRepository.save(session);

        assertThatThrownBy(() -> sessionJoinService.register(savedSessionId,NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
