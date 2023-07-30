package nextstep.courses.domain.curriculum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nextstep.courses.DuplicatedException;
import nextstep.courses.domain.session.Session;

public class Curriculums {

  private Set<Curriculum> curriculums = new HashSet<>();

  public Curriculums() {
  }

  public Curriculums(List<Curriculum> curriculums) {
    this(new HashSet<>(curriculums));
  }

  public Curriculums(Set<Curriculum> curriculums) {
    this.curriculums = curriculums;
  }

  public void addCurriculum(Curriculum curriculum) {
    validateCurriculum(curriculum);
    curriculums.add(curriculum);
  }

  private void validateCurriculum(Curriculum curriculum) {
    if (hasSession(curriculum.getSessionId())) {
      throw new DuplicatedException("해당 기수에 중복되는 강의가 존재합니다.");
    }
  }

  public boolean hasSession(Session session) {
    return hasSession(session.getId());
  }

  public boolean hasSession(Long sessionId) {
    return curriculums.stream()
        .anyMatch(curriculum -> curriculum.hasSession(sessionId));
  }
}
