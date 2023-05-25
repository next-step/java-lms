package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

  @DisplayName("과정(Course)은 기수(Batch)을 개설할 수 있다.")
  @Test
  public void addSession() {
    Course course = new Course("Test", 1L);
    Batch batch = course.createdBatch(1L);

    assertThat(batch.checkBatchNo(1)).isTrue();
  }
}
