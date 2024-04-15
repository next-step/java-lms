package nextstep.courses.infrastructure;

import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.coverImage.ImageType.PNG;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class CoverImageRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("커버 이미지 저장 테스트")
    void testSave() {
        CoverImage savedCoverImage = coverImageRepository.save(new CoverImage("이미지", 1024 * 1024, 300, 200, PNG));
        assertThat(savedCoverImage.getId()).isNotNull();
    }
}