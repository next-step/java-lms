package nextstep.sessions.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.users.domain.NsUserGroup;
import nextstep.users.domain.NsUserNsUserGroup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionRegistrationTest {

  @Test
  void accept_성공() {
    NsUserNsUserGroup nsUserNsUserGroup = new NsUserNsUserGroup(1L, 1L, 1L);
    SessionRegistration sessionRegistration = new SessionRegistration(1, new NsUserGroup(1L, "우아한테크코스"));

    sessionRegistration.accept(
        List.of(nsUserNsUserGroup), new Student(1L, 1L, 1L, StudentStatus.WAITING, LocalDateTime.now(), null));
  }

  @Test
  void accept_실패() {
    NsUserNsUserGroup nsUserNsUserGroup = new NsUserNsUserGroup(1L, 1L, 1L);
    SessionRegistration sessionRegistration = new SessionRegistration(1, new NsUserGroup(2L, "우아한테크캠프Pro"));

    Assertions.assertThatThrownBy(() -> sessionRegistration.accept(
        List.of(nsUserNsUserGroup), new Student(1L, 1L, 1L, StudentStatus.WAITING, LocalDateTime.now(), null)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("강의에 선발된 인원만 수강신청이 가능합니다");
  }
}