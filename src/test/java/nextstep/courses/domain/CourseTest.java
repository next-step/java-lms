package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import nextstep.courses.domain.batch.Batch;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

  private Course c1;
  private Session s1;
  private Session s2;

  @BeforeEach
  public void setUp() {
    c1 = new Course("ssafy", 1L);
    s1 = new Session("tdd", "tdd-img", LocalDateTime.now(),
        LocalDateTime.now().plusMonths(2), SessionType.PAID, 1, 1L);
    s2 = new Session("atdd", "atdd-img", LocalDateTime.now(),
        LocalDateTime.now().plusMonths(1), SessionType.PAID, 30, 1L);
  }

  @DisplayName("과정(Course)은 기수(Batch)을 개설할 수 있다.")
  @Test
  public void addBatch() {
    Batch batch = c1.createdBatch(1L);

    assertThat(batch.checkBatchNo(1)).isTrue();
  }

  @DisplayName("과정(Course)은 기수(Batch) 단위로 여러 개의 강의(Session)를 가질 수 있다.")
  @Test
  public void addSession() {
    Batch batch1 = c1.createdBatch(1L);
    Batch batch2 = c1.createdBatch(1L);
    assertAll(
        () -> c1.addSession(1, s1),
        () -> assertThat(batch1.hasSession(s1)).isTrue(),
        () -> c1.addSession(1, s2),
        () -> assertThat(batch1.hasSession(s2)).isTrue(),
        () -> c1.addSession(2, s2),
        () -> assertThat(batch2.hasSession(s2)).isTrue()
    );
  }
}
