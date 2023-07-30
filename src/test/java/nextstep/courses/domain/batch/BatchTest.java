package nextstep.courses.domain.batch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import nextstep.courses.DuplicatedException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.curriculum.Curriculums;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BatchTest {

  private Course c1;
  private Batches bs1;
  private Session s1;
  private Session s2;

  @BeforeEach
  public void setUp() {
    c1 = new Course("ssafy", 1L);
    bs1 = new Batches();
    s1 = new Session("tdd", "tdd-img", LocalDateTime.now(), LocalDateTime.now().plusMonths(2),
        SessionType.PAID, 1, 1L);
    s2 = new Session("atdd", "atdd-img", LocalDateTime.now(),
        LocalDateTime.now().plusMonths(1), SessionType.PAID, 30, 1L);
  }

  @DisplayName("과정(Course)은 기수(Batch) 단위로 여러 개의 강의(Session)를 가질 수 있다.")
  @Test
  public void addSession() {
    Batch batch1 = c1.createdBatch(1L, bs1);
    Curriculums curriculums1 = new Curriculums();
    Batch batch2 = c1.createdBatch(1L, bs1);
    Curriculums curriculums2 = new Curriculums();
    assertThat(bs1.getSize()).isEqualTo(2);

    assertAll(
        () -> batch1.addSession(s1, curriculums1),
        () -> assertThat(batch1.hasSession(s1, curriculums1)).isTrue(),
        () -> batch1.addSession(s2, curriculums1),
        () -> assertThat(batch1.hasSession(s2, curriculums1)).isTrue(),
        () -> batch2.addSession(s2, curriculums2),
        () -> assertThat(batch2.hasSession(s2, curriculums2)).isTrue()
    );
  }

  @DisplayName("기수(Batch)는 중복되는 강의(Session)를 추가할 수 없다.")
  @Test
  public void addSession_throwException_ifDuplicatedSessionAdd() {
    Batch batch = c1.createdBatch(1L, bs1);
    Curriculums curriculums1 = new Curriculums();
    batch.addSession(s1, curriculums1);

    assertThatThrownBy(() -> batch.addSession(s1, curriculums1))
        .isInstanceOf(DuplicatedException.class);
  }
}
