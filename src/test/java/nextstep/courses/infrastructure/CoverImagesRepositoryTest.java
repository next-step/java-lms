package nextstep.courses.infrastructure;

import nextstep.courses.domain.InstructorTest;
import nextstep.courses.tobe.domain.*;
import nextstep.courses.tobe.domain.session.TobeCoverImages;
import nextstep.courses.tobe.infrastructure.TobeJdbcCoverImagesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.CourseTest.C1;
import static nextstep.courses.domain.session.DateRangeTest.DATE_RANGE1;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.TobeCoverImageTest.TOBE_COVER_IMAGE_LIST1;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImagesRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TobeCoverImagesRepository coverImagesRepository;
    private TobeFreeSession freeSession;

    @BeforeEach
    void setUp() {
        coverImagesRepository = new TobeJdbcCoverImagesRepository(jdbcTemplate);
        freeSession = new TobeFreeSession(1L,
                C1.getId(),
                DATE_RANGE1,
                TOBE_COVER_IMAGE_LIST1,
                InstructorTest.IN1,
                ProcessStatus.PROCESS,
                RecruitmentStatus.OPEN,
                1L,
                START,
                START);
    }

    @Test
    void crud() {
        TobeCoverImages coverImages = new TobeCoverImages(TOBE_COVER_IMAGE_LIST1.get(0));
        coverImages.add(TOBE_COVER_IMAGE_LIST1.get(1));
        int freeSessionSavedCount = coverImagesRepository.saveAll(coverImages);
        assertThat(freeSessionSavedCount).isEqualTo(2);

        TobeCoverImages savedCoverImages = coverImagesRepository.findAllBySessionId(1L);
        LOGGER.info("savedCoverImages = {}", savedCoverImages);
        LOGGER.info("coverImages = {}", coverImages);

        assertThat(coverImages.size()).isEqualTo(savedCoverImages.size());
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from cover_image");
        jdbcTemplate.execute("ALTER TABLE cover_image ALTER COLUMN id RESTART WITH 1");
    }
}
