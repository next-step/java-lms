package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE_1;
import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE_2;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcCoverImageDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageDAO.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CoverImageDAO coverImageDAO;

    @BeforeEach
    void setUp() {
        coverImageDAO = new JdbcCoverImageDAO(jdbcTemplate);
    }

    @Test
    void saveAll_find() {
        coverImageDAO.saveAll(List.of(NORMAL_COVER_IMAGE_1, NORMAL_COVER_IMAGE_2));
        List<CoverImage> savedCoverImages = coverImageDAO.findBySessionId(0L);
        assertThat(savedCoverImages.size()).isEqualTo(2);
        LOGGER.debug("CoverImages : {}", savedCoverImages);
    }
}
