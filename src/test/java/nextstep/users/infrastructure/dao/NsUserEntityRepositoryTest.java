package nextstep.users.infrastructure.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@JdbcTest
class NsUserEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(NsUserEntityRepositoryTest.class);

  @Autowired
  private JdbcOperations jdbcOperations;

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private NsUserEntityRepository nsUserEntityRepository;

  @BeforeEach
  void setUp() {
    nsUserEntityRepository = new NsUserEntityRepository(jdbcOperations,namedParameterJdbcTemplate);
  }

  @Test
  void findByIds() {
    List<NsUserEntity> userIds = nsUserEntityRepository.findByUserIds(List.of(1L,2L));
    assertThat(userIds).isNotNull();
    LOGGER.debug("NsUserEntity: {}", userIds);
  }

  @Test
  void findByUserId() {
    Optional<NsUserEntity> nsUserEntity = nsUserEntityRepository.findByUserId("javajigi");
    assertThat(nsUserEntity).isNotNull();
    LOGGER.debug("NsUserEntity: {}", nsUserEntity);
  }
}