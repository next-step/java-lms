package nextstep.courses.app;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SessionJoinServiceTest {
    @Autowired
    private SessionJoinService sessionJoinService;

    @Autowired
    private SessionJoinRepository sessionJoinRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("강의 등록")
    void test01() {
        Session session = aSession().withId(1L).withSessionStatus(SessionStatus.OPEN).build();
        long savedSessionId = sessionRepository.save(session);

        sessionJoinService.register(savedSessionId, List.of(NsUserTest.JAVAJIGI.getUserId()));

        List<SessionJoin> findSessionJoins = sessionJoinRepository.findAllBySessionId(savedSessionId);
        assertThat(findSessionJoins).hasSize(1);
    }

    @Test
    @DisplayName("강의 등록 - 최대 강의 수 초과")
    void test02() {
        Session session = aSession().withId(1L).withSessionStatus(SessionStatus.OPEN)
                                    .withMaxUserCount(1).build();
        long savedSessionId = sessionRepository.save(session);

        assertThatThrownBy(() -> sessionJoinService.register(savedSessionId, List.of(NsUserTest.JAVAJIGI.getUserId(),
                                                                                     NsUserTest.SANJIGI.getUserId())))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
