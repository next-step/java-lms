package nextstep.courses.domain.course;

import org.junit.jupiter.api.BeforeEach;

public class CourseTest {

  private Course c1;

  @BeforeEach
  public void setUp() {
    c1 = new Course(1L, "ssafy", 1L);
  }
}
