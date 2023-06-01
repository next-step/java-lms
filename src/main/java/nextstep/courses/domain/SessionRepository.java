package nextstep.courses.domain;

public interface SessionRepository {

  Session findById(Long id);

  Long saveSignUpHistory(Long sessionId, Long userId);

  void saveApproved(Long sessionId, Long userId);

  void saveRejected(Long sessionId, Long userId);
}
