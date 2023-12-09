package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.ImageSize;
import nextstep.courses.domain.SessionImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class JdbcSessionImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionImageRepository sessionImageRepository;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
    }

    @Test
    void save() {
        SessionImage sessionImage = new SessionImage(1024, "jpg", new ImageSize(300, 200));
        int count = sessionImageRepository.save(sessionImage);
        assertThat(count).isEqualTo(1);
        SessionImage save = sessionImageRepository.findById(1L);
        assertThat(save.getFileSize()).isEqualTo(sessionImage.getFileSize());
    }
}