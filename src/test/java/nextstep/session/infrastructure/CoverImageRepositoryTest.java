package nextstep.session.infrastructure;

import nextstep.session.domain.CoverImage;
import nextstep.session.domain.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
        CoverImage coverImage = new CoverImage.Builder()
                .id(1L)
                .fileName("test cover image")
                .fileSize(100)
                .imageFormat("jpg")
                .imageSize(300, 200)
                .build();

        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);

        CoverImage savedCoverImage = coverImageRepository.findById(1L);
        assertThat(coverImage.getFileName()).isEqualTo(savedCoverImage.getFileName());

        LOGGER.debug("CoverImage: {}", savedCoverImage);
    }
}
