package nextstep.courses.domain.curriculum;

import java.util.HashSet;
import java.util.Set;
import nextstep.courses.DuplicatedException;
import nextstep.courses.domain.curriculum.Curriculum;

public class Curriculums {

  private Set<Curriculum> curriculums = new HashSet<>();

  public Curriculums() {
  }

  public void addCurriculum(Curriculum curriculum) {
    validateCurriculum(curriculum);
    curriculums.add(curriculum);
  }

  private void validateCurriculum(Curriculum curriculum) {
    if (hasCurriculum(curriculum)) {
      throw new DuplicatedException("해당 기수에 중복되는 강의가 존재합니다.");
    }
  }

  public boolean hasCurriculum(Curriculum curriculum) {
    return curriculums.contains(curriculum);
  }
}
