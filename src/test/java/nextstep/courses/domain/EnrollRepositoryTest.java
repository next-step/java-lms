package nextstep.courses.domain;

import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.JdbcEnrollRepository;
import nextstep.fixture.TestFixture;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollRepositoryTest {
    private static final Logger LOG = LoggerFactory.getLogger(EnrollRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollRepository enrollRepository;


    @BeforeEach
    void setUp() {
        TestFixture.fixtureInit();
        enrollRepository = new JdbcEnrollRepository(jdbcTemplate);
    }

    @AfterEach
    void wrapUp() {
        enrollRepository.deleteAll();
    }

    @DisplayName("저장한다")
    @Test
    public void save() {
        //given
        Session session = TestFixture.LIME_SESSION;
        session.setSessionId(new SessionId(22L));
        session.toRecruitingState();
        NsUser user = TestFixture.BADAJIGI;
        //when
        Enroll enroll = session.register(user.getUserCode(), 0);
        //then
        enrollRepository.save(enroll);
    }

    @DisplayName("ID 기준으로 조회한다")
    @Test
    public void findById() {
        //given
        //when
        //then
        enrollRepository.findById(new EnrollId(1L));
    }

    @DisplayName("전체 데이터를 조회한다")
    @Test
    public void findAll() {
        //given
        //when
        //then
        enrollRepository.findAll();
    }

    @DisplayName("전체 데이터를 삭제한다")
    @Test
    public void deleteAll() {
        //given
        //when
        //then
        enrollRepository.deleteAll();
    }

    @DisplayName("CRUD 전 과정을 검증한다")
    @Test
    public void crud() {
        //given
        //when
        //then
        fail();
    }
}