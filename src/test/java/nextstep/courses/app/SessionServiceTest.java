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

import static nextstep.fixtures.SessionFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SessionServiceTest {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

    @DisplayName("저장")
    @Test
    void save() {
        sessionService.save(testSession1());

        Session findSession = sessionRepository.findById(1L);

        assertThat(findSession.getId()).isNotNull();
    }

    @DisplayName("조회")
    @Test
    void select() {
        sessionRepository.save(testSession1());

        Session findSession = sessionService.findById(1L);

        assertThat(findSession.getId()).isEqualTo(1L);
    }
}
