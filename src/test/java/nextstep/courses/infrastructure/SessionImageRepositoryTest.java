package nextstep.courses.infrastructure;

import nextstep.courses.InvalidImageFormatException;
import nextstep.courses.domain.image.SessionImageRepository;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.domain.image.SessionImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionImageRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionImageRepository sessionImageRepository;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("강의는 강의커버 이미지를 갖는다.")
    void save_강의커버이미지() throws InvalidImageFormatException {
        SessionImage sessionImage = new SessionImage(1L, "커버이미지", 1L, 1024 * 1024, 300, 200, ImageType.PNG);
        int count = sessionImageRepository.save(sessionImage);
        assertThat(count).isEqualTo(1);
        Optional<SessionImage> savedImage = sessionImageRepository.findBySessionId(1L);
        assertThat(sessionImage.getName()).isEqualTo(savedImage.get().getName());
        LOGGER.debug("image: {}", savedImage);
    }

}
