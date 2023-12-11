package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
public class JdbcCoverImageRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @DisplayName("아이디에 해당하는 커버이미지를 가져온다.")
    @Test
    void findById() {
        CoverImage coverImage = coverImageRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 커버이미지가 존재하지 않습니다."));

        assertThat(coverImage.getFileName()).isEqualTo("12345.jpg");
    }
}
