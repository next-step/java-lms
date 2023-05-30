package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class CoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    private String imagePath = "/resources/image/coverImage";

    private CoverImage coverImage = CoverImage.of(imagePath);

    @BeforeEach
    void 초기화() {
        jdbcTemplate.execute("alter table cover_image alter column id restart with 1");
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("커버 이미지 정보를 삭제한다.")
    void 커버이미지_삭제() {
        coverImageRepository.save(coverImage);
        CoverImage saved = coverImageRepository.findById(1L);

        assertAll(
            () -> assertThat(coverImageRepository.delete(saved)).isEqualTo(1L),
            () -> assertThat(coverImageRepository.findById(saved.id())).isEqualTo(CoverImage.of(""))
        );
    }

    @Test
    @DisplayName("커버 이미지 정보를 변경한다.")
    void 커버이미지_수정() {
        coverImageRepository.save(coverImage);
        CoverImage saved = coverImageRepository.findById(1L);

        String updatePath = "/resources/image/newPath";

        CoverImage update = CoverImage.of(saved.id(), updatePath);
        assertAll(
            () -> assertThat(coverImageRepository.update(update)).isEqualTo(1L),
            () -> assertThat(coverImageRepository.findById(saved.id())).isEqualTo(CoverImage.of(saved.id(), updatePath))
        );
    }

    @Test
    @DisplayName("커버 이미지를 저장한다.")
    void 커버이미지_저장() {
        assertAll(
            () -> assertThat(coverImageRepository.save(coverImage)).isEqualTo(1L),
            () -> assertThat(coverImageRepository.findById(1L)).isEqualTo(CoverImage.of(1L, imagePath))
        );
    }
}