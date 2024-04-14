package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.SessionCoverImage;
import nextstep.courses.domain.SessionCoverImageTest;
import nextstep.courses.domain.SessionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionCoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcCourseRepository courseRepository;
    private JdbcSessionRepository sessionRepository;
    private JdbcSessionCoverImageRepository repository;

    @BeforeEach
    public void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        repository = new JdbcSessionCoverImageRepository(jdbcTemplate);
    }

    @DisplayName("CRUD 테스트")
    @Test
    void crud() {
        courseRepository.save(CourseTest.C1);
        sessionRepository.save(SessionTest.FREE_S1);
        int count = repository.save(SessionCoverImageTest.CI);
        assertThat(count).isEqualTo(1);
        SessionCoverImage savedSessionCoverImage = repository.findById(SessionCoverImageTest.CI.getId());
        assertThat(savedSessionCoverImage.getId()).isEqualTo(SessionCoverImageTest.CI.getId());
    }
}