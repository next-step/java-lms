package nextstep.courses.infrastructure;

import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import nextstep.courses.domain.coverImage.CoverImages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }


    @DisplayName("테이블 저장 및 조회 쿼리 테스트")
    @Test
    void save() {
        // given
        CoverImage coverImage = new CoverImage(1L, "path", 400, "gif", 300, 200);
        coverImageRepository.save(coverImage);
        // when
        CoverImages coverImages = coverImageRepository.findBySessionId(1L);
        // then
        Assertions.assertThat(coverImages.getCoverImages()).hasSize(1);
    }
}