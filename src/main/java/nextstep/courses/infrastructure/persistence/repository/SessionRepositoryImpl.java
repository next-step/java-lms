package nextstep.courses.infrastructure.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.ApproveStatus;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.persistence.dao.ImageEntityRepository;
import nextstep.courses.infrastructure.persistence.dao.SessionEnrollmentEntityRepository;
import nextstep.courses.infrastructure.persistence.dao.SessionEntityRepository;
import nextstep.courses.infrastructure.persistence.entity.ImageEntity;
import nextstep.courses.infrastructure.persistence.entity.SessionEnrollmentEntity;
import nextstep.courses.infrastructure.persistence.entity.SessionEntity;
import nextstep.users.infrastructure.dao.NsUserEntityRepository;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class SessionRepositoryImpl implements SessionRepository {

  private final SessionEntityRepository sessionEntityRepository;
  private final SessionEnrollmentEntityRepository sessionEnrollmentEntityRepository;
  private final ImageEntityRepository imageEntityRepository;
  private final NsUserEntityRepository nsUserEntityRepository;

  public SessionRepositoryImpl(SessionEntityRepository sessionEntityRepository,
      SessionEnrollmentEntityRepository sessionEnrollmentEntityRepository, ImageEntityRepository imageEntityRepository,
      NsUserEntityRepository NsUserEntityRepository) {
    this.sessionEntityRepository = sessionEntityRepository;
    this.sessionEnrollmentEntityRepository = sessionEnrollmentEntityRepository;
    this.imageEntityRepository = imageEntityRepository;
    this.nsUserEntityRepository = NsUserEntityRepository;
  }

  @Override
  public Session findById(Long sessionId) {
    SessionEntity sessionEntity = findSessionEntity(sessionId);

    List<Student> students = toSessionStudents(sessionId);

    Image image = toSessionImage(sessionEntity.getCoverImageId());

    return sessionEntity.toDomain(students, image);
  }

  @Override
  public Long saveSignUpHistory(Long sessionId, Long userId) {
    SessionEnrollmentEntity sessionEnrollmentEntity = new SessionEnrollmentEntity(sessionId, userId,
        ApproveStatus.WAITING);
    return sessionEnrollmentEntityRepository.save(sessionEnrollmentEntity);
  }

  @Override
  public void saveApproved(Long sessionId, Long userId) {
    SessionEnrollmentEntity sessionEnrollmentEntity = sessionEnrollmentEntityRepository
        .findBySessionIdAndUserId(sessionId, userId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수강 신청 내역입니다."));
    sessionEnrollmentEntity.approved();
    sessionEnrollmentEntityRepository.update(sessionEnrollmentEntity);
  }

  @Override
  public void saveRejected(Long sessionId, Long userId) {
    SessionEnrollmentEntity sessionEnrollmentEntity = sessionEnrollmentEntityRepository
        .findBySessionIdAndUserId(sessionId, userId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수강 신청 내역입니다."));
    sessionEnrollmentEntity.rejected();
    sessionEnrollmentEntityRepository.update(sessionEnrollmentEntity);
  }

  private SessionEntity findSessionEntity(Long sessionId) {
    return sessionEntityRepository.findById(sessionId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 세션입니다."));
  }

  private Image toSessionImage(Long coverImageId) {
    ImageEntity imageEntity = imageEntityRepository.findById(coverImageId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이미지입니다."));
    return imageEntity.toDomain();
  }

  private List<Student> toSessionStudents(Long sessionId) {
    List<SessionEnrollmentEntity> sessionEnrollmentEntities = sessionEnrollmentEntityRepository.findBySessionId(sessionId);

    List<Student> students = sessionEnrollmentEntities.stream()
        .map(sessionEnrollmentEntity -> {
          NsUserEntity userEntity = nsUserEntityRepository.findByUserKeyId(
                  sessionEnrollmentEntity.getUserId())
              .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
          return new Student(userEntity.toDomain(), sessionEnrollmentEntity.getApproveStatus());
        }).collect(Collectors.toList());

    return students;
  }
}
