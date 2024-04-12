package nextstep.courses.domain;

import java.util.List;

public interface SessionImageRepository {
  int save(SessionImage sessionImage);

  Long saveAndGetGeneratedKey(SessionImage sessionImage);

  SessionImage findById(Long id);

  List<SessionImage> findBySessionId(Long sessionId);
}
