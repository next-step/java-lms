package nextstep.courses.domain;

import nextstep.member.Student;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ChargedSessionTest {
  @ParameterizedTest
  @ValueSource(strings = { "PREPARING", "CLOSED" })
  void 모집중이지_않음(SessionStatus status) {
    ChargedSession session = new ChargedSession(1L, new File("test"), status, List.of(),
            LocalDate.now(), LocalDate.now().plusMonths(1L), 5, 10000L);
    assertThatThrownBy(() -> session.addStudent(new Student(1L)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("수강생 모집중인 강의가 아닙니다.");
  }

  @Test
  void 최대_수강인원_초과() {
    ChargedSession session = new ChargedSession(1L, new File("test"), SessionStatus.OPEN, List.of(new Student(1L), new Student(2L), new Student(3L), new Student(4L)),
            LocalDate.now(), LocalDate.now().plusMonths(1L), 3, 10000L);
    assertThatThrownBy(() -> session.addStudent(new Student(1L)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("최대 수강인원을 초과하였습니다.");
  }

  @ParameterizedTest
  @ValueSource(longs = { 5000L, 15000L })
  void 결제_정보_일치하지_않음(Long input) {
    ChargedSession session = new ChargedSession(1L, new File("test"), SessionStatus.OPEN, List.of(new Student(1L)),
            LocalDate.now(), LocalDate.now().plusMonths(1L), 3, 10000L);
    assertThatThrownBy(() -> session.addStudent(new Student(1L, List.of(new Payment("TEST_PAYMENT", 1L, 1L, input)))))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("일치하는 결제 정보가 없습니다.");
  }

  @Test
  void 정상_수강_신청() {
    ChargedSession session = new ChargedSession(1L, new File("test"), SessionStatus.OPEN, List.of(new Student(1L)),
            LocalDate.now(), LocalDate.now().plusMonths(1L), 3, 10000L);
    session.addStudent(new Student(1L, List.of(new Payment("TEST_PAYMENT", 1L, 1L, 10000L))));
    assertThat(session.numberOfStudents()).isEqualTo(2);
  }
}
