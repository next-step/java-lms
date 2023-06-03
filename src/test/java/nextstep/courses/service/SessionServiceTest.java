package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static nextstep.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@SpringBootTest
class SessionServiceTest {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

    @DisplayName("Session 저장")
    @Test
    void save() {
        sessionService.save(aSession().build());
        Session foundSession = sessionRepository.findById(1L);
        assertThat(foundSession.getId()).isNotNull();
    }

    @DisplayName("Session 조회")
    @Test
    void findById() {
        sessionRepository.save(aSession().withId(1L).build());
        Session foundSession = sessionService.findById(1L);
        assertThat(foundSession.getId()).isEqualTo(1L);
    }

    @DisplayName("Session(강의) 수강등록")
    @Test
    void register() {
        Session session = aSession().withId(1L).build();
        long sessionId = sessionRepository.save(session);

        Session foundSession = sessionRepository.findById(sessionId);
        sessionService.register(foundSession, NsUserTest.JAVAJIGI);
        sessionService.register(foundSession, NsUserTest.SANJIGI);

        List<String> sessionUserIds = sessionRepository.findSessionUserIdsBySessionId(sessionId);
        assertThat(sessionUserIds).hasSize(2);
    }

    @DisplayName("초과 등록")
    @Test
    void register_실패_인원초과() {
        Session session = aSession().withId(1L).withSessionRegistration(
                aSessionRegistrationBuilder().withStudents(
                        aStudentsBuilder()
                                .withMaxUserCount(1)
                                .withUsers(NsUserTest.JAVAJIGI)
                                .build()
                ).build()
        ).build();

        sessionRepository.save(session);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> sessionService.register(session, NsUserTest.SANJIGI))
                .withMessageMatching("최대 수강 인원을 초과했습니다.");


    }
}
