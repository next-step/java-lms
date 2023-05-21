package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoin;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SessionServiceTest {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("저장")
    void test01() {
        sessionService.save(aSession().build());

        Session findSession = sessionRepository.findById(1L);

        assertThat(findSession.getId()).isNotNull();
    }


    @Test
    @DisplayName("조회")
    void test02() {
        sessionRepository.save(aSession().withId(1L).build());

        Session findSession = sessionService.findById(1L);

        assertThat(findSession.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("강의 등록")
    void test03() {
        Session session = aSession().withId(1L).withSessionStatus(SessionStatus.OPEN).build();
        sessionRepository.save(session);

        sessionService.register(session, List.of(NsUserTest.JAVAJIGI));

        List<SessionJoin> findSessionJoins = sessionRepository.findAllSessionJoinBySessionId(session.getId());
        assertThat(findSessionJoins).hasSize(1);
    }

    @Test
    @DisplayName("강의 등록 - 최대 강의 수 초과")
    void test04() {
        Session session = aSession().withId(1L).withSessionStatus(SessionStatus.OPEN)
                                    .withMaxUserCount(1).build();
        sessionRepository.save(session);

        assertThatThrownBy(() -> sessionService.register(session, List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
