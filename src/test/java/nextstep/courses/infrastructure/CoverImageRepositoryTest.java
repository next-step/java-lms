package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql(value = "/course_image.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session savedSession = sessionRepository.findById(1L).get();
        CoverImage coverImage = new CoverImage(9_000L, "GIF", 300L, 200L, savedSession, 0L);
        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1L);
        CoverImage savedCoverImage = coverImageRepository.findById(1L).get();
        assertThat(savedCoverImage.getCapacity()).isEqualTo(9_000L);
        assertThat(savedCoverImage.getType()).isEqualTo("GIF");
        assertThat(savedCoverImage.getWidth()).isEqualTo(300L);
        assertThat(savedCoverImage.getHeight()).isEqualTo(200L);
    }
}
