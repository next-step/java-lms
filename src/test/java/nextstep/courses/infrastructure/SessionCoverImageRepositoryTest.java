package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.ImageExtension;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static nextstep.courses.domain.fixture.SessionCoverImageFixture.coverImage;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionCoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionCoverImageRepository sessionCoverImageRepository;

    @BeforeEach
    void setUp() {
        sessionCoverImageRepository = new JdbcSessionCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("save()")
    void save() {
        SessionCoverImage image = coverImage(ImageExtension.GIF.value());
        SessionCoverImageEntity entity = new SessionCoverImageEntity(image, LocalDateTime.now());

        int count = sessionCoverImageRepository.save(entity);

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("findById()")
    void findById() {
        String extension = ImageExtension.GIF.value();
        SessionCoverImage image = coverImage(extension);
        SessionCoverImageEntity entity = new SessionCoverImageEntity(image, LocalDateTime.now());
        sessionCoverImageRepository.save(entity);

        SessionCoverImageEntity saveEntity = sessionCoverImageRepository.findById(1L);

        assertThat(saveEntity.getExtension()).isEqualTo(extension);
    }

}
