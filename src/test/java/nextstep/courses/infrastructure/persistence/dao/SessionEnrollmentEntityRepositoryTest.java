package nextstep.courses.infrastructure.persistence.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

@JdbcTest
class SessionEnrollmentEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SessionEnrollmentEntityRepositoryTest.class);


  @Autowired
  private JdbcOperations jdbcOperations;

  private SessionEnrollmentEntityRepository sessionEnrollmentEntityRepository;

  @BeforeEach
  void setUp() {
    sessionEnrollmentEntityRepository = new SessionEnrollmentEntityRepository(jdbcOperations);
  }

  @Test
  void findUserIdsBySessionId() {
    List<Long> userIdsBySessionId = sessionEnrollmentEntityRepository.findUserIdsBySessionId(100L);
    assertThat(userIdsBySessionId).isNotNull();
    assertThat(userIdsBySessionId).hasSize(2);
    LOGGER.debug("userIdsBySessionId: {}", userIdsBySessionId);
  }
}