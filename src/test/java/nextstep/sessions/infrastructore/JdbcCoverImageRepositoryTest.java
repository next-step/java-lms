package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.CoverImage;
import nextstep.sessions.domain.EnableExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@JdbcTest
class JdbcCoverImageRepositoryTest {

    private static final CoverImage COVER_IMAGE1 = new CoverImage(1L, 1L, 1200, 800, 340797, "playground.jpeg", EnableExtension.JPEG, "playground.jpeg");
    private static final CoverImage COVER_IMAGE2 = new CoverImage(2L, 1L, 300, 200, 999, "example.png", EnableExtension.PNG, "example.png");


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void save() {
        CoverImage coverImage = new CoverImage(1L, 600, 400, 9999, "example.svg", "svg", "/images/example.svg");
        long id = coverImageRepository.save(coverImage);
        assertThat(id).isEqualTo(3L); // 더미
    }

    @Test
    void findById() {
        CoverImage savedCoverImage = coverImageRepository.findById(1L);
        assertThat(savedCoverImage)
                .isEqualTo(COVER_IMAGE1);
    }

    @Test
    void findBySessionId() {
        List<CoverImage> result = coverImageRepository.findBySessionId(1L);
        assertThat(result)
                .hasSize(2)
                .containsExactly(COVER_IMAGE1, COVER_IMAGE2);
    }

    @Test
    void saveAll() {
        List<CoverImage> saveData = List.of(
                new CoverImage(1L, 600, 400, 9999, "example.svg", "svg", "/images/example.svg"),
                new CoverImage(1L, 1200, 800, 1111, "example2.png", "png", "/images/example2.png")
        );

        assertThatNoException().isThrownBy(() -> coverImageRepository.saveAll(saveData));

        List<CoverImage> result = coverImageRepository.findBySessionId(1L);
        assertThat(result).hasSize(4);
    }

}
