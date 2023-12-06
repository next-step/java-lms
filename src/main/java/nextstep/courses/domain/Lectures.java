package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Lectures {

  private final Set<Lecture> lectures = new HashSet<>();

  public Lectures() {
  }

  public void add(Lecture lecture) {
    this.lectures.add(lecture);
  }

  public int size() {
    return lectures.size();
  }
}
