package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class EnrollmentRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        NsUsers saveBeforeNsUsers = enrollmentRepository.findBySessionId(1L);
        int count = enrollmentRepository.save(1L, 1L);
        assertThat(count).isOne();
        NsUsers saveAfterNsUsers = enrollmentRepository.findBySessionId(1L);
        List<NsUser> users = saveBeforeNsUsers.diffWith(saveAfterNsUsers);
        assertThat(users).contains(new NsUser(
                1L,
                "javajigi",
                "test",
                "자바지기",
                "javajigi@slipp.net"));
        LOGGER.debug("NsUsers: {}", saveAfterNsUsers);
    }
}
