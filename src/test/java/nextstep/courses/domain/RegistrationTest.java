package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class RegistrationTest {
  private final Session session = new FreeSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L),
            List.of(new SessionImage(300, 200, "jpg", 1024, "TEST", 1L)),
          OpenStatus.OPEN,
          RecruitStatus.OPEN);

  private final NsUser student = new NsUser(1L, "JAVAJIGI", "password", "자바지기", "javajigi@gmail.com");

  @Test
  void 객체_생성() {
    new Registration(session, student);
  }
}
