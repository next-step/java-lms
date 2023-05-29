package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionProgressStatus;
import nextstep.courses.domain.SessionRecruitmentStatus;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.fixture.SessionFixture;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class SessionServiceTest {
    @Autowired
    private SessionService sessionService;

    @Test
    void save() {
        Session session = SessionFixture.createRecruitingSession();

        Session savedSession = sessionService.save(session, 1L);
        Session findSession = sessionService.findById(savedSession.getId());

        assertThat(findSession.getId()).isEqualTo(savedSession.getId());
    }

    @Test
    void findByCourseId() {
        sessionService.save(SessionFixture.createRecruitingSession(), 2L);
        sessionService.save(SessionFixture.createRecruitingSession(), 2L);
        List<Session> findSession = sessionService.findByCourseId(2L);

        assertThat(findSession).hasSize(2);
    }

    @Test
    void enroll() {
        Session session = sessionService.save(SessionFixture.createRecruitingSession(), 1L);

        sessionService.enroll(session, NsUserTest.JAVAJIGI);
        List<NsUser> sessionUsers = sessionService.findAllUserBySessionId(session.getId());

        assertThat(sessionUsers).hasSize(1);
        assertThat(sessionUsers.get(0).getUserId()).isEqualTo(NsUserTest.JAVAJIGI.getUserId());
    }

    @Test
    @DisplayName("강의 최대 인원이 초과된 경우 수강신청할 수 없다")
    void saveSessionUser_Fail1() {
        Session session = sessionService.save(SessionFixture.create(SessionProgressStatus.PROGRESSING, SessionRecruitmentStatus.RECRUITING, 1), 1L);
        sessionService.enroll(session, NsUserTest.JAVAJIGI);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sessionService.enroll(session, NsUserTest.SANJIGI))
                .withMessageMatching("강의 최대 수강 인원이 초과되었습니다.");
    }

    @Test
    @DisplayName("강의가 모집중이 아닌 경우 수강신청할 수 없다")
    void saveSessionUser_Fail2() {
        Session session = sessionService.save(SessionFixture.create(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.NOT_RECRUITING, 1), 1L);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sessionService.enroll(session, NsUserTest.JAVAJIGI))
                .withMessageMatching("모집중인 강의가 아닙니다.");
    }
}
