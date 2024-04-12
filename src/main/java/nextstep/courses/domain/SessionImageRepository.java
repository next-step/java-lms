package nextstep.courses.domain;

public interface SessionImageRepository {
  int save(SessionImage sessionImage);

  Long saveAndGetGeneratedKey(SessionImage sessionImage);

  SessionImage findById(Long id);
}
