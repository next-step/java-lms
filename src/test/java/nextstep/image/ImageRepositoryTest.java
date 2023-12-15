package nextstep.image;

import nextstep.image.domain.ImageRepository;
import nextstep.image.infrastructure.JdbcImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class ImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void findById() {
        assertThat(imageRepository.findById(1L)).isPresent();
    }
}
