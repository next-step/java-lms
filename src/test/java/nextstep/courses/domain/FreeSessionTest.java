package nextstep.courses.domain;

import nextstep.member.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeSessionTest {
  @ParameterizedTest
  @ValueSource(strings = { "PREPARING", "CLOSED" })
  void 모집중이지_않은_경우_수강신청(SessionStatus status) {
    FreeSession session = new FreeSession(1L, LocalDate.now(), LocalDate.now().plusMonths(1L), new File("test"),
            status, List.of(new Student(1L)));
    assertThatThrownBy(() -> session.addStudent(new Student(1L)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("수강생 모집중인 강의가 아닙니다.");
  }

  @Test
  void 정상_수강신청() {
    FreeSession session = new FreeSession(1L, LocalDate.now(), LocalDate.now().plusMonths(1L),
            new File("test"), SessionStatus.OPEN, List.of());
    session.addStudent(new Student(1L));

    assertThat(session.numberOfStudents()).isEqualTo(1);
  }
}
