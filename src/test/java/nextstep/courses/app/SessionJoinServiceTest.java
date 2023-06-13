package nextstep.courses.app;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static nextstep.fixtures.SessionFixtures.testSession1;
import static nextstep.fixtures.SessionFixtures.testSession5;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SessionJoinServiceTest {
    @Autowired
    private SessionJoinService sessionJoinService;
    @Autowired
    private SessionJoinRepository sessionJoinRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @DisplayName("등록")
    @Test
    void enroll() {
        Session session = testSession1();
        long savedSessionId = sessionRepository.save(session);

        sessionJoinService.register(savedSessionId, JAVAJIGI);

        List<SessionJoin> findSessionJoins = sessionJoinRepository.findAllBySessionId(savedSessionId);
        assertThat(findSessionJoins).hasSize(1);
    }

    @DisplayName("강의 등록시 인원 수 초과")
    @Test
    void maxUserException() {
        Session session = testSession5();
        long savedSessionId = sessionRepository.save(session);

        assertThatThrownBy(() -> sessionJoinService.register(savedSessionId, JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강 신청 승인")
    @Test
    void approve() {
        sessionRepository.save(testSession1());
        long savedSessionId = testSession1().getId();
        sessionJoinService.register(savedSessionId, JAVAJIGI);

        sessionJoinService.approve(savedSessionId, JAVAJIGI);

        List<SessionJoin> sessionJoins = sessionJoinRepository.findAllBySessionId(savedSessionId);
        assertThat(sessionJoins).hasSize(1)
                .extracting("session.id", "nsUser.id", "sessionJoinStatus")
                .containsExactly(tuple(savedSessionId, JAVAJIGI.getId(), SessionJoinStatus.APPROVAL));
    }

    @DisplayName("수강 신청 거절")
    @Test
    void reject() {
        sessionRepository.save(testSession1());
        long savedSessionId = testSession1().getId();
        sessionJoinService.register(savedSessionId, JAVAJIGI);

        sessionJoinService.reject(savedSessionId, JAVAJIGI);

        List<SessionJoin> sessionJoins = sessionJoinRepository.findAllBySessionId(savedSessionId);
        assertThat(sessionJoins).hasSize(1)
                .extracting("session.id", "nsUser.id", "sessionJoinStatus")
                .containsExactly(tuple(savedSessionId, JAVAJIGI.getId(), SessionJoinStatus.REJECTION));
    }

}
