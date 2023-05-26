package nextstep.courses.domain;

public interface SessionRepository {

  Session findById(Long id);

  Long saveSignUpHistory(Long sessionId, Long userId);
}
