package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageFileName;
import nextstep.courses.domain.CoverImagePixel;
import nextstep.courses.domain.CoverImageRepository;
import nextstep.courses.domain.CoverImageSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        jdbcTemplate.execute("ALTER TABLE cover_image ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    @DisplayName("coverImage 생성 테스트")
    void create() {
        CoverImage coverImage = new CoverImage(
                1L,
                new CoverImageFileName("test.jpg"),
                new CoverImageSize(1024),
                new CoverImagePixel(300, 200));

        int actual = coverImageRepository.save(coverImage);
        int expected = 1;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("coverImage 조회 테스트")
    void read() {
        CoverImage coverImage = new CoverImage(
                1L,
                new CoverImageFileName("test.jpg"),
                new CoverImageSize(1024),
                new CoverImagePixel(300, 200));

        coverImageRepository.save(coverImage);

        CoverImage findCoverImage = coverImageRepository.findById(1L);

        assertAll(
                () -> assertThat(coverImage.fileSize()).isEqualTo(findCoverImage.fileSize()),
                () -> assertThat(coverImage.fileName()).isEqualTo(findCoverImage.fileName()),
                () -> assertThat(coverImage.width()).isEqualTo(findCoverImage.width()),
                () -> assertThat(coverImage.height()).isEqualTo(findCoverImage.height()),
                () -> assertThat(coverImage.sessionId()).isEqualTo(findCoverImage.sessionId()));
    }

    @Test
    @DisplayName("coverImage 갱신 테스트")
    void update() {
        CoverImage before = new CoverImage(
                1L,
                new CoverImageFileName("test.jpg"),
                new CoverImageSize(1024),
                new CoverImagePixel(300, 200));

        coverImageRepository.save(before);

        CoverImage after = new CoverImage(
                1L,
                1L,
                new CoverImageFileName("update.jpg"),
                new CoverImageSize(2048),
                new CoverImagePixel(600, 400));

        int actual = coverImageRepository.update(after);
        int expected = 1;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("coverImage 삭제 테스트")
    void delete() {
        CoverImage coverImage = new CoverImage(
                1L,
                new CoverImageFileName("test.jpg"),
                new CoverImageSize(1024),
                new CoverImagePixel(300, 200));

        coverImageRepository.save(coverImage);

        int actual = coverImageRepository.delete(1L);
        int expected = 1;

        assertThat(actual).isEqualTo(expected);
    }
}
