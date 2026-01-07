package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.cover.CoverImage;
import nextstep.courses.domain.session.cover.CoverImages;
import nextstep.courses.repository.CoverImageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
public class CoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage coverImage = new CoverImage(100, "test.png", 600, 400);
        coverImageRepository.save(coverImage, 10L);

        CoverImages images = coverImageRepository.findBySessionId(10L);
        assertThat(images.size()).isEqualTo(1);

        CoverImage saved = images.getValues().get(0);
        assertThat(saved.getImageName()).isEqualTo(coverImage.getImageName());
    }
}
