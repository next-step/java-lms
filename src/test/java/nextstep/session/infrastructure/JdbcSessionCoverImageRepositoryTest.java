package nextstep.session.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.session.domain.SessionCoverImage;
import nextstep.session.domain.SessionCoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class JdbcSessionCoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionCoverImageRepository sessionCoverImageRepository;

    @BeforeEach
    void setUp() {
        sessionCoverImageRepository = new JdbcSessionCoverImageRepository(jdbcTemplate);
    }

    @Test
    void 저장() {
        SessionCoverImage coverImage = new SessionCoverImage(1L, 900, 600, 10000, "png");
        int count = sessionCoverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void 강의_커버_이미지_조회() {
        SessionCoverImage coverImage = new SessionCoverImage(1L, 900, 600, 10000, "jpg");
        sessionCoverImageRepository.save(coverImage);
        SessionCoverImage savedCoverImage = sessionCoverImageRepository.findBySessionId(1L);
        assertThat(savedCoverImage.isSameImageType("jpg")).isTrue();

    }
}
