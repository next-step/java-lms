package nextstep.users.infrastructure;

import nextstep.courses.infrastructure.CourseRepositoryTest;
import nextstep.users.domain.NsStudent;
import nextstep.users.domain.NsStudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcNsStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
public class NsStudentRepositoryTest {

    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NsStudentRepository nsStudentRepository;

    @BeforeEach
    void setUp() {
        nsStudentRepository = new JdbcNsStudentRepository(jdbcTemplate);
    }

    @Test
    void save() {
        NsStudent nsStudent = new NsStudent(null, "javajigi", "password", "name", "javajigi@slipp.net");
        nsStudent.register(1L);
        nsStudent.register(2L);
        int[] count = nsStudentRepository.save(nsStudent);
        assertThat(count).hasSize(2);
//        Course savedCourse = courseRepository.findById(1L);
//        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());
//        LOGGER.debug("Course: {}", savedCourse);
    }
}
