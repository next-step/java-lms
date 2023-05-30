package nextstep.courses.infrastructure.persistence.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
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
    assertThat(studentSize).isEqualTo(3);
  }

  @Test
  void 존재하지_않는_Session_500L을_조회하는_경우_빈_리스트_반환() {
    List<Long> studentKeyIds = sessionEnrollmentEntityRepository.findUserKeyIdsBySessionId(500L);
    assertThat(studentKeyIds).isEmpty();
  }

  @Test
  void 임시_데이터에서_이미_수강신청된_내역중_강의_id와_유저_id를_통해서_신청_내역_조회() {
    Optional<SessionEnrollmentEntity> alreadyApprovedHistory = sessionEnrollmentEntityRepository.findBySessionIdAndUserId(
        100L, 1L);

    assertThat(alreadyApprovedHistory).isPresent();
    assertThat(alreadyApprovedHistory.get().approveStatus()).isEqualTo(ApproveStatus.APPROVED);
  }

  @Test
  void 임시_데이터에서_이미_수강신청된_내역중_강의_id와_유저_id를_통해서_신청_내역_조회_실패() {
    Optional<SessionEnrollmentEntity> alreadyApprovedHistory = sessionEnrollmentEntityRepository.findBySessionIdAndUserId(
        100L, 3L);

    assertThat(alreadyApprovedHistory).isEmpty();
  }

  @Test
  void 수강대기상태_이력을_수강승인_상태로_변경() {
    SessionEnrollmentEntity beforeApproved = new SessionEnrollmentEntity(200L, 2L, ApproveStatus.WAITING);
    sessionEnrollmentEntityRepository.save(beforeApproved);

    SessionEnrollmentEntity afterApproved = new SessionEnrollmentEntity(200L, 2L, ApproveStatus.APPROVED);
    sessionEnrollmentEntityRepository.update(afterApproved);

    Optional<SessionEnrollmentEntity> approvedHistory = sessionEnrollmentEntityRepository.findBySessionIdAndUserId(
        200L, 2L);

    assertThat(approvedHistory).isPresent();
    assertThat(approvedHistory.get().approveStatus()).isEqualTo(ApproveStatus.APPROVED);
  }

  @Test
  void 수강대기상태_이력을_거절_상태로_변경() {
    SessionEnrollmentEntity beforeRejected = new SessionEnrollmentEntity(200L, 2L, ApproveStatus.WAITING);
    sessionEnrollmentEntityRepository.save(beforeRejected);

    SessionEnrollmentEntity afterRejected = new SessionEnrollmentEntity(200L, 2L, ApproveStatus.REJECTED);
    sessionEnrollmentEntityRepository.update(afterRejected);

    Optional<SessionEnrollmentEntity> approvedHistory = sessionEnrollmentEntityRepository.findBySessionIdAndUserId(
        200L, 2L);

    assertThat(approvedHistory).isPresent();
    assertThat(approvedHistory.get().approveStatus()).isEqualTo(ApproveStatus.REJECTED);
  }
}