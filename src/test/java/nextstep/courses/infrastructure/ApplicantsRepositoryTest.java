package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.Applicants;
import nextstep.courses.domain.course.session.ApplicantsRepository;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ApplicantsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicantsRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ApplicantsRepository applicantsRepository;

    @BeforeEach
    void setUp() {
        applicantsRepository = new JdbcApplicantsRepository(jdbcTemplate);
    }

    /* data.sql 파일에 이미 저장한 데이터로 테스트 합니다. */
    @Test
    void find_success() {
        Applicants applicants = applicantsRepository.findAllBySessionId(10L);
        NsUser nsUser_1 = applicants.find(0);
        NsUser nsUser_2 = applicants.find(1);

        assertThat(applicants.size()).isEqualTo(2);
        assertThat(nsUser_1.getId()).isEqualTo(1L);
        assertThat(nsUser_2.getId()).isEqualTo(2L);
        LOGGER.debug("Applicants: {}", applicants);
    }
}
