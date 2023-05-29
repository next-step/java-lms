package nextstep.courses.infrastructure.persistence.dao;

import static nextstep.courses.domain.SessionStatus.RECRUITING;
import static nextstep.courses.domain.SessionType.FREE;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import nextstep.courses.domain.SessionInfo;
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
  void 임시_데이터에_존재하는_Session_100L을_조회_및_값_검증() {
    Optional<SessionEntity> sessionEntity = sessionEntityRepository.findById(100L);
    assertThat(sessionEntity).isNotNull();

    sessionEntity.ifPresent(entity -> {
      assertThat(entity.getId()).isEqualTo(100L);
      assertThat(entity.getSessionInfo()).isEqualTo(new SessionInfo("Session 1 Belong To Course 1", "Session 1 Description"));
      assertThat(entity.getCoverImageId()).isEqualTo(100L);
      assertThat(entity.getSessionType()).isEqualTo(FREE);
      assertThat(entity.getSessionStatus()).isEqualTo(RECRUITING);
      assertThat(entity.getMaxEnrollmentSize()).isEqualTo(2);
    });
  }

  @Test
  void 임시_데이터에_존재하지_않는_경우_101L_Session_조회_실패() {
    Optional<SessionEntity> optionalSessionEntity = sessionEntityRepository.findById(101L);
    assertThat(optionalSessionEntity).isEmpty();
  }
}