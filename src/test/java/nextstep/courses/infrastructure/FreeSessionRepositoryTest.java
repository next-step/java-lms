package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.domain.SessionProgressState;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.FreeSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class FreeSessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreeSessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FreeSessionRepository freeSessionRepository;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void insert() {
        CoverImage coverImage = new CoverImage("images/test.gif", 1000_000, "gif", 300, 200, LocalDateTime.now());
        List<CoverImage> coverImages = new ArrayList<>(List.of(coverImage));

        FreeSession freeSession = new FreeSession(
                coverImages,
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionProgressState.PREPARING,
                true,
                LocalDateTime.now()
        );
        int count = freeSessionRepository.save(courseRepository.findById(10L), freeSession);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void findById() {
        FreeSession saveSession = freeSessionRepository.findById(11L);
        assertThat(saveSession).isNotNull();
        LOGGER.debug("FreeSession : {}", saveSession);
    }

    @Test
    void findByCourseId() {
        List<FreeSession> saveSessionList = freeSessionRepository.findByCourseId(10L);
        assertThat(saveSessionList).isNotNull();
        LOGGER.debug("FreeSession : {}", saveSessionList);
    }


}
