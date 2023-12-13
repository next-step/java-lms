package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcCoverImageDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageDAO.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CoverImageDAO coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageDAO(jdbcTemplate);
    }

    @Test
    void save_find() {
        Long sessionId = coverImageRepository.save(NORMAL_COVER_IMAGE);
        assertThat(sessionId).isEqualTo(0L);
        CoverImage savedCoverImage = coverImageRepository.findBySessionId(0L);
        assertThat(savedCoverImage.size()).isEqualTo(NORMAL_COVER_IMAGE.size());
        LOGGER.debug("CoverImage : {}", savedCoverImage);
    }
}
