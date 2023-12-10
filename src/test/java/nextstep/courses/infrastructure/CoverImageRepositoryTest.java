package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.repository.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImageRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage coverImage = new CoverImage("images/test.jpeg", 1000_000, "jpeg", 300, 200, LocalDateTime.now());
        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);

        CoverImage saveCoverImage = coverImageRepository.findById(1L);
        assertThat(coverImage).isEqualTo(saveCoverImage);
        LOGGER.debug("CoverImage : {}", coverImage);
        LOGGER.debug("saveCoverImage : {}", saveCoverImage);
    }

}
