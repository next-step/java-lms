package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionImageRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private SessionImageRepository sessionImageRepository;

  @BeforeEach
  void setUp() {
    sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
  }

  @Test
  void crud() {
    SessionImage sessionImage = new SessionImage(300, 200, "jpeg", 1024, "TEST", 1L);
    int count = sessionImageRepository.save(sessionImage);
    assertThat(count).isEqualTo(1);

    SessionImage savedSessionImage = sessionImageRepository.findById(1L);
    assertThat(sessionImage.getFileName()).isEqualTo(savedSessionImage.getFileName());
    LOGGER.debug("SessionImage: {}", savedSessionImage);

    sessionImageRepository.save(new SessionImage(2L,300, 200, "jpeg", 1024, "TEST", 1L));
    List<SessionImage> savedSessionImages = sessionImageRepository.findBySessionId(1L);
    assertThat(savedSessionImages.size()).isEqualTo(2);

//    jdbcTemplate.update("DELETE FROM session_image");
  }
}
