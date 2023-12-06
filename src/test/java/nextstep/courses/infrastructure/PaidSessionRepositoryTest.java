package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.FreeSessionRepository;
import nextstep.courses.repository.PaidSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class PaidSessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreeSessionRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PaidSessionRepository paidSessionRepository;
    private CourseRepository courseRepository;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        paidSessionRepository = new JdbcPaidSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void insert() {
        PaidSession session = new PaidSession(1L, coverImage(), LocalDate.now(), LocalDate.of(2023, 12, 31), 10, 10_000L);
        int count = paidSessionRepository.save(session, courseId());
        assertThat(count).isEqualTo(1);
    }

    @Test
    void select() {
        PaidSession session = paidSessionRepository.findById(3L);

        assertAll(
                () -> assertThat(session.id()).isEqualTo(3L),
                () -> assertThat(session.type()).isEqualTo("P"),
                () -> assertThat(session.maxStudents()).isEqualTo(30)
        );
    }

    private Long courseId() {
        return courseRepository.findById(2L).getId();
    }

    private CoverImage coverImage() {
        return coverImageRepository.findById(2L);
    }
}