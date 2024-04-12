package nextstep.courses.infrastructure;

import nextstep.config.BeanConfig;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BeanConfig.class)
public class SessionImageRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private SimpleJdbcInsert simpleJdbcInsert;

  private SessionImageRepository sessionImageRepository;

  @BeforeEach
  void setUp() {
    sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate, simpleJdbcInsert);
  }

  @Test
  void crud() {
    SessionImage sessionImage = new SessionImage(300, 200, "jpeg", 1024, "TEST");
    int count = sessionImageRepository.save(sessionImage);
    assertThat(count).isEqualTo(1);
    SessionImage savedSessionImage = sessionImageRepository.findById(1L);
    assertThat(sessionImage.getFileName()).isEqualTo(savedSessionImage.getFileName());
    LOGGER.debug("SessionImage: {}", savedSessionImage);
  }
}
