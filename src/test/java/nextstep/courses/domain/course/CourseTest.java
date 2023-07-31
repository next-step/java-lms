package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.batch.Batch;
import nextstep.courses.domain.batch.Batches;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

  private Course c1;
  private Batches bs1;

  @BeforeEach
  public void setUp() {
    c1 = new Course(1L, "ssafy", 1L);
    bs1 = new Batches();
  }

  @DisplayName("과정(Course)은 기수(Batch)을 개설할 수 있다.")
  @Test
  public void addBatch() {
    Batch batch = c1.createdBatch(1L, bs1);

    assertThat(batch.checkBatchNo(1)).isTrue();
    assertThat(bs1.getSize()).isEqualTo(1);
  }
}
