package nextstep.courses.infrastructure.session.image;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@JdbcTest
class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void init() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    @Transactional
    @DisplayName("새로운 커버 이미지를 생성한다.")
    void test() {
        final CoverImageEntity coverImageEntity = new CoverImageEntity("gif", 10000L, 300, 200, 1L);

        final long savedCoverImageEntityId = coverImageRepository.save(coverImageEntity);
        final CoverImageEntity savedCoverImageEntity = coverImageRepository.findById(savedCoverImageEntityId).get();

        assertThat(savedCoverImageEntity.id())
                .isEqualTo(savedCoverImageEntityId);
    }
}
