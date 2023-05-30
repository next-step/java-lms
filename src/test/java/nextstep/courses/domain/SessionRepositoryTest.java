package nextstep.courses.domain;

import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
public class SessionRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        TestFixture.fixtureInit();
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @AfterEach
    void wrapUp() {
        sessionRepository.deleteAll();
    }

    @DisplayName("저장 기능을 검증한다")
    @Test
    public void save() {
        //given
        Session session = TestFixture.LIME_SESSION;
        //when
        //then
        sessionRepository.save(session);
    }

    @DisplayName("ID 기준 조회 기능을 검증한다")
    @Test
    public void findById() {
        //given
        //when
        //then
        sessionRepository.findBySessionId(new SessionId(1L));
    }

    @DisplayName("전체 데이터 조회 기능을 검증한다")
    @Test
    public void findAll() {
        //given
        //when
        //then
        sessionRepository.findAll();
    }

    @DisplayName("전체 데이터를 삭제기능을 검증한다")
    @Test
    public void deleteAll() {
        //given
        //when
        //then
        sessionRepository.deleteAll();
    }
    @DisplayName("CRUD 전 과정을 통합해서 검증한다")
    @Test
    public void crud() {
        //given
        Session session1 = TestFixture.LEMON_SESSION;
        Session session2 = TestFixture.LIME_SESSION;
        //when
        Session save1 = sessionRepository.save(session1);
        Session save2 = sessionRepository.save(session2);
        Session find1 = sessionRepository.findBySessionId(save1.getSessionId()).orElseThrow();
        Session find2 = sessionRepository.findBySessionId(save2.getSessionId()).orElseThrow();
        List<SessionId> findAll = sessionRepository.findAll()
                .stream()
                .map(Session::getSessionId)
                .collect(Collectors.toList());
        //then
        assertAll("CRUD 과정을 검증한다",
                () -> assertThat(find1.getSessionId())
                        .as("저장과 조회의 데이터가 동일함을 검증한다")
                        .isEqualTo(save1.getSessionId()),
                () -> assertThat(find2.getSessionId())
                        .as("저장과 조회의 데이터가 동일함을 검증한다")
                        .isEqualTo(save2.getSessionId()),
                () -> assertThat(findAll)
                        .as("findAll 조회 메서드의 동작을 검증한다")
                        .contains(find1.getSessionId(), find2.getSessionId())
        );

    }
}