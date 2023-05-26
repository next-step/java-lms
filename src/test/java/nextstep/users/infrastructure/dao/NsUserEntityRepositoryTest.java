package nextstep.users.infrastructure.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class NsUserEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(NsUserEntityRepositoryTest.class);
  private final NsUserEntityRepository nsUserEntityRepository;

  public NsUserEntityRepositoryTest(@Autowired JdbcTemplate jdbcTemplate) {
    this.nsUserEntityRepository = new NsUserEntityRepository(jdbcTemplate);
  }


  @Test
  void 임시_데이터에_존재하는_1L_2L을_통해서_유저_목록_조회_및_값_검증() {
    List<NsUserEntity> userEntity = nsUserEntityRepository.findByUserKeyIds(List.of(1L, 2L));
    assertThat(userEntity).isNotNull();

    assertThat(userEntity.size()).isEqualTo(2);
    assertThat(userEntity.get(0).getUserId()).isEqualTo("javajigi");
    assertThat(userEntity.get(1).getUserId()).isEqualTo("sanjigi");
  }

  @Test
  void 임시_데이터에_존재하는_아이디_javajigi_을_통해서_유저_단건_조회_및_값_검증() {
    Optional<NsUserEntity> userEntity = nsUserEntityRepository.findByUserId("javajigi");
    assertThat(userEntity).isNotNull();

    userEntity.ifPresent(
        user -> {
          assertThat(user.getUserId()).isEqualTo("javajigi");
          assertThat(user.getName()).isEqualTo("자바지기");
        }
    );
  }

  @Test
  void 임시_데이터에_존재하는지_않는_아이디로_조회_실패() {
    Optional<NsUserEntity> userEntity = nsUserEntityRepository.findByUserId("test");
    assertThat(userEntity).isEmpty();
  }
}