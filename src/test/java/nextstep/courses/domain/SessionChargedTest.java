package nextstep.courses.domain;

import nextstep.member.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SessionChargedTest {
  @ParameterizedTest
  @ValueSource(strings = { "PREPARING", "CLOSED" })
  void 모집중이지_않음(SessionStatus status) {
    SessionCharged session = new SessionCharged(1L, new File("test"), status, List.of(),5, 10000);
    assertThatThrownBy(() -> session.addStudent(new Student(1L)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("강의가 열렸을 경우에만 수강 신청 가능합니다.");
  }

  @Test
  void 최대_수강인원_초과() {
    SessionCharged session = new SessionCharged(1L, new File("test"), SessionStatus.OPEN, List.of(new Student(1L), new Student(2L), new Student(3L), new Student(4L)),3, 10000);
    assertThatThrownBy(() -> session.addStudent(new Student(1L)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("최대 수강인원을 초과하였습니다.");
  }

  @Test
  void 정상_수강_신청() {
    SessionCharged session = new SessionCharged(1L, new File("test"), SessionStatus.OPEN, List.of(new Student(1L)), 3, 10000);
    session.addStudent(new Student(1L));
    assertThat(session.numberOfStudents()).isEqualTo(2);
  }
}
