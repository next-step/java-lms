package nextstep.courses.domain;

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
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
public class EnrollRepositoryTest {
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
        Session session1 = TestFixture.LIME_SESSION;
        session1.setSessionId(new SessionId(22L));
        session1.toRecruitingState();
        Session session2 = TestFixture.LEMON_SESSION;
        session2.setSessionId(new SessionId(33L));
        session2.toRecruitingState();
        NsUser user = TestFixture.BADAJIGI;
        Enroll enroll1 = session1.register(user.getUserCode(), 0);
        Enroll enroll2 = session2.register(user.getUserCode(), 0);
        //when
        Enroll save1 = enrollRepository.save(enroll1);
        Enroll save2 = enrollRepository.save(enroll2);
        Enroll find1 = enrollRepository.findById(save1.getEnrollId()).orElseThrow();
        Enroll find2 = enrollRepository.findById(save2.getEnrollId()).orElseThrow();
        List<EnrollId> all = enrollRepository.findAll().stream().map(Enroll::getEnrollId).collect(Collectors.toList());
        //then
        assertAll("",
                () -> assertThat(save1.getEnrollId())
                        .as("저장과 조회의 데이터가 동일함을 검증한다")
                        .isEqualTo(find1.getEnrollId()),
                () -> assertThat(save2.getEnrollId())
                        .as("저장과 조회의 데이터가 동일함을 검증한다")
                        .isEqualTo(find2.getEnrollId()),
                () -> assertThat(all)
                        .as("저장과 조회의 데이터가 동일함을 검증한다")
                        .contains(find1.getEnrollId(), find2.getEnrollId(), save1.getEnrollId(), save2.getEnrollId())
        );
    }
}