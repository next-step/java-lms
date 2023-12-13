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
import java.util.List;

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
    void insert() {
        CoverImage coverImage = new CoverImage("images/test.jpeg", 1000_000, "jpeg", 300, 200, LocalDateTime.now());
        int count = coverImageRepository.save(coverImage, 10L);
        assertThat(count).isEqualTo(1);


    }

    @Test
    void findById() {
        CoverImage saveCoverImage = coverImageRepository.findById(10L);
        assertThat(saveCoverImage).isNotNull();
        LOGGER.debug("saveCoverImage : {}", saveCoverImage);
    }

    @Test
    void findBySessionId() {
        List<CoverImage> saveCoverImages = coverImageRepository.findBySessionId(10L);
        assertThat(saveCoverImages).isNotNull().hasSize(1);
        LOGGER.debug("saveCoverImages : {}", saveCoverImages);

    }

}
