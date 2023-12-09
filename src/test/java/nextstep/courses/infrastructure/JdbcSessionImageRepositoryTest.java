package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImageRepository;
import nextstep.courses.domain.SessionImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@JdbcTest
class JdbcSessionImageRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionImageRepository sessionImageRepository;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("성공 - 강의 이미지를 조회 한다.")
    void success_find_registered_users() {
        SessionImages sessionImages = sessionImageRepository.findBy(1L);

        assertThat(sessionImages.sessionImages()).hasSize(2)
                .extracting("size", "extension", "width", "height")
                .containsExactly(
                        tuple(1000, "jpg", 300, 200),
                        tuple(500, "png", 600, 400)
                );
    }

}
