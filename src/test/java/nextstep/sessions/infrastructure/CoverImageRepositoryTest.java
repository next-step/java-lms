package nextstep.sessions.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.sessions.domain.data.vo.CoverImage;
import nextstep.sessions.repository.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }


    @Test
    void 커버_이미지_생성_및_조회() {
        CoverImage coverImage = new CoverImage("image.png", 102400, 300, 200);

        int count = coverImageRepository.saveCoverImage(1, coverImage);
        assertThat(count).isEqualTo(1);

        CoverImage savedCoverImage = coverImageRepository.findCoverImageBySessionId(1);
        assertThat(savedCoverImage.imageType()).isEqualTo(coverImage.imageType());
    }
}
