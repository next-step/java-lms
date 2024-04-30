package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage coverImage = new CoverImage(9_000L, "GIF", 300L, 200L, 0L, 0L);
        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1L);
        CoverImage savedCoverImage = coverImageRepository.findById(1L).get();
        assertThat(savedCoverImage.getCapacity()).isEqualTo(9_000L);
        assertThat(savedCoverImage.getType()).isEqualTo("GIF");
        assertThat(savedCoverImage.getWidth()).isEqualTo(300L);
        assertThat(savedCoverImage.getHeight()).isEqualTo(200L);
    }
}
