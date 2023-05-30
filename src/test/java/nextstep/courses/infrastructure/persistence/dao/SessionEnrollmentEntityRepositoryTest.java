package nextstep.courses.infrastructure.persistence.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import nextstep.courses.domain.ApproveStatus;
import nextstep.courses.infrastructure.persistence.entity.SessionEnrollmentEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;


@JdbcTest
class SessionEnrollmentEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SessionEnrollmentEntityRepositoryTest.class);

  private final SessionEnrollmentEntityRepository sessionEnrollmentEntityRepository;

  public SessionEnrollmentEntityRepositoryTest(@Autowired JdbcTemplate jdbcTemplate) {
    this.sessionEnrollmentEntityRepository = new SessionEnrollmentEntityRepository(jdbcTemplate);
  }


  /**
   * VALUES (고유 ID, 세션 ID, 수강생 ID, 생성일시, 수정일시)
   * VALUES (100, 100, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   *        (200, 100, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   *        (300, 200, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
   */
  @Test
  void 임시_데이터에서_수강생이_2명_있는_Session_100L_조회_및_값_검증() {
    List<Long> userIdsBySessionId = sessionEnrollmentEntityRepository.findUserKeyIdsBySessionId(100L);
    assertThat(userIdsBySessionId).isNotNull();
    assertThat(userIdsBySessionId).hasSize(2);
  }


  @Test
  void Session_200L에_학생_2L이_수강신청한_내역_대기상태로_저장() {
    SessionEnrollmentEntity sessionEnrollmentEntity = new SessionEnrollmentEntity(200L, 2L, ApproveStatus.WAITING);
    Long id = sessionEnrollmentEntityRepository.save(sessionEnrollmentEntity);
    assertThat(id).isNotNull();

    int studentSize = sessionEnrollmentEntityRepository.findUserKeyIdsBySessionId(200L).size();
    assertThat(studentSize).isEqualTo(2);
  }

  @Test
  void 존재하지_않는_Session_500L을_조회하는_경우_빈_리스트_반환() {
    List<Long> studentKeyIds = sessionEnrollmentEntityRepository.findUserKeyIdsBySessionId(500L);
    assertThat(studentKeyIds).isEmpty();
  }
}