package nextstep.courses.domain.curriculum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nextstep.courses.DuplicatedException;

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
    if (hasCurriculum(curriculum)) {
      throw new DuplicatedException("해당 기수에 중복되는 강의가 존재합니다.");
    }
  }

  public boolean hasCurriculum(Curriculum curriculum) {
    return curriculums.contains(curriculum);
  }
}
