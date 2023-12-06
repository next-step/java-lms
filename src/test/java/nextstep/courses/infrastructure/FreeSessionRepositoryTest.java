package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Session;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.FreeSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class FreeSessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreeSessionRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FreeSessionRepository freeSessionRepository;
    private CourseRepository courseRepository;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void insert() {
        Session freeSession = new FreeSession(1L, coverImage(), LocalDate.now(), LocalDate.of(2023, 12, 31));
        int count = freeSessionRepository.save(freeSession, courseId());
        assertThat(count).isEqualTo(1);
    }

    @Test
    void select() {
        Session session = freeSessionRepository.findById(2L);
        assertThat(session.id()).isEqualTo(2L);
        assertThat(session.type()).isEqualTo("F");
    }

    private Long courseId() {
        return courseRepository.findById(2L).getId();
    }

    private CoverImage coverImage() {
        return coverImageRepository.findById(2L);
    }
}