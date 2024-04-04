package nextstep.courses.domain;

import nextstep.member.Student;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionFreeTest {
  @ParameterizedTest
  @ValueSource(strings = { "PREPARING", "CLOSED" })
  void 모집중이지_않은_경우_수강신청(SessionStatus status) {
    SessionCharged session = new SessionCharged(1L, new File("test"), status, List.of(),5, 10000);
    assertThatThrownBy(() -> session.addStudent(new Student(1L)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("강의가 열렸을 경우에만 수강 신청 가능합니다.");
  }
}
