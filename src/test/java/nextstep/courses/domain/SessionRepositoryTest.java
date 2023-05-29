package nextstep.courses.domain;

import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class SessionRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        TestFixture.fixtureInit();
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
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
        sessionRepository.findById(new SessionId(1L));
    }

    @DisplayName("전체 데이터 조회 기능을 검증한다")
    @Test
    public void findAll() {
        //given
        //when
        //then
        sessionRepository.findAll();
    }

    @DisplayName("CRUD 전 과정을 통합해서 검증한다")
    @Test
    public void crud() {
        //given
        //when
        //then
        fail();
    }
}