package nextstep.session.infrastructure;

import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.image.CoverImageRepository;
import nextstep.session.support.CoverImageTestBuilder;
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
        CoverImage coverImage = new CoverImageTestBuilder()
                .withId(1L)
                .build();

        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);

        CoverImage savedCoverImage = coverImageRepository.findById(1L);
        assertThat(coverImage.getFileName()).isEqualTo(savedCoverImage.getFileName());
        assertThat(coverImage.getSessionId()).isEqualTo(savedCoverImage.getSessionId());

        LOGGER.debug("CoverImage: {}", savedCoverImage);
    }
}
