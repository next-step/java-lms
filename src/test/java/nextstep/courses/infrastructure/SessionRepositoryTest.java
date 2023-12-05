package nextstep.courses.infrastructure;

import nextstep.courses.domain.cource.Image;
import nextstep.courses.domain.cource.ImageRepository;
import nextstep.courses.domain.cource.SessionRepository;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, imageRepository);
    }

    @Test
    void crud() {
        Image findImage = 이미지_저장_및_반환();
        Session session = Session.ofPaid(Period.from(), findImage, 1_000L, 1L);

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session saveSession = sessionRepository.findById(1L);
        assertThat(saveSession.type()).isEqualTo(saveSession.type());
        LOGGER.debug("saveSession: {}", saveSession);
    }

    private Image 이미지_저장_및_반환() {
        imageRepository.save(Image.from());
        Image findImage = imageRepository.findById(1L);
        return findImage;
    }
}
