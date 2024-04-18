package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.session.image.CoverImageRepository;
import nextstep.courses.domain.session.image.CoverImageTest;

@JdbcTest
@Transactional
class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("새로운 커버 이미지를 생성한다.")
    void test() {
        final int savedCount = coverImageRepository.save(CoverImageTest.COVER_IMAGE);

        assertThat(savedCount).isOne();
    }
}
