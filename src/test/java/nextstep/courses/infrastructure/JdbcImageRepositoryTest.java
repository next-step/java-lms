package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.fixture.SessionFixtures.COVER_IMAGE;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    public void 이미지를_저장하고_조회할_수_있다() throws Exception {
        int count = imageRepository.save(COVER_IMAGE);
        assertThat(count).isEqualTo(1);

        CoverImage image = imageRepository.findById(COVER_IMAGE.getId());
        assertThat(image.getId()).isEqualTo(COVER_IMAGE.getId());
        assertThat(image.getUrl()).isEqualTo(COVER_IMAGE.getUrl());
    }

}