package nextstep.courses.infrastructure.persistence.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.SessionEntity;
import nextstep.users.infrastructure.repository.UserRepositoryImplTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class SessionEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImplTest.class);

  private final SessionEntityRepository sessionEntityRepository;

  public SessionEntityRepositoryTest(@Autowired JdbcTemplate jdbcTemplate) {
    this.sessionEntityRepository = new SessionEntityRepository(jdbcTemplate);
  }


  @Test
  void findById() {
    Optional<SessionEntity> sessionEntity = sessionEntityRepository.findById(100L);
    assertThat(sessionEntity).isNotNull();
    LOGGER.debug("SessionEntity: {}", sessionEntity);
  }
}