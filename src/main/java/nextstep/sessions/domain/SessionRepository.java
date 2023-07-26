package nextstep.sessions.domain;

import java.util.Optional;

/**
 * id를 리턴해서 Controller에서 Location 헤더에 resource를 지정해 주고 싶었으나,
 * 기존 CourseRepository의 save()가 count를 리턴하므로 팀 룰이라고 판단해 count를 리턴하도록 구현
 *
 * @return id
 *
 * @date 2023-06-05
 * @author JerryK026
 */

public interface SessionRepository {
  int save(Session session);

  Optional<Session> findById(Long id);

  void update(Session session);

  default void foo() {

  }
}
