package nextstep.users.infrastructure;

import nextstep.courses.infrastructure.CourseRepositoryTest;
import nextstep.users.domain.NsStudent;
import nextstep.users.domain.NsStudentRepository;
import nextstep.users.domain.NsTeacher;
import nextstep.users.domain.NsTeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class NsTeacherRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NsTeacherRepository nsTeacherRepository;

    @BeforeEach
    void setUp() {
        nsTeacherRepository = new JdbcNsTeacherRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        NsTeacher nsTeacher = new NsTeacher(null, "javajigi", "password", "name", "javajigi@slipp.net");
        nsTeacher.chargeSession(1L);
        nsTeacher.chargeSession(2L);
        int[] count = nsTeacherRepository.save(nsTeacher);
        assertThat(count).hasSize(2);
    }
}
