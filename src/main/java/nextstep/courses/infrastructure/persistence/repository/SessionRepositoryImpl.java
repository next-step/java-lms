package nextstep.courses.infrastructure.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.infrastructure.persistence.dao.ImageEntityRepository;
import nextstep.courses.infrastructure.persistence.dao.SessionEnrollmentEntityRepository;
import nextstep.courses.infrastructure.persistence.dao.SessionEntityRepository;
import nextstep.courses.infrastructure.persistence.entity.ImageEntity;
import nextstep.courses.infrastructure.persistence.entity.SessionEntity;
import nextstep.users.domain.NsUser;
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

    List<NsUser> students = toSessionStudents(sessionId);

    Image image = toSessionImage(sessionEntity.getCoverImageId());

    return sessionEntity.toDomain(students, image);
  }

  @Override
  public Long saveSignUpHistory(Long sessionId, Long userId) {
    return sessionEnrollmentEntityRepository.save(sessionId, userId);
  }

  private SessionEntity findSessionEntity(Long sessionId) {
    SessionEntity sessionEntity = sessionEntityRepository.findById(sessionId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 세션입니다."));
    return sessionEntity;
  }

  private Image toSessionImage(Long coverImageId) {
    ImageEntity imageEntity = imageEntityRepository.findById(coverImageId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이미지입니다."));
    return imageEntity.toDomain();
  }

  private List<NsUser> toSessionStudents(Long sessionId) {
    List<Long> userKeyIds = sessionEnrollmentEntityRepository.findUserKeyIdsBySessionId(sessionId);
    List<NsUserEntity> users = nsUserEntityRepository.findByUserKeyIds(userKeyIds);
    return users.stream().map(NsUserEntity::toDomain)
        .collect(Collectors.toList());
  }
}
