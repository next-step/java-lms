package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SessionUserStatusTest {

  @ParameterizedTest(name = "기존 수강 인원들은 승인 상태로 반환")
  @NullSource
  public void check_null(String status) {
    assertThat(SessionUserStatus.valueOfSessionUserStatus(status)).isEqualTo(SessionUserStatus.APPROVAL);
  }

  @ParameterizedTest(name = "신청, 승인, 거절이 아닌 경우 IllegalArgumentException throw")
  @EmptySource
  public void check_not_신청_승인_거절(String status) {
    assertThatThrownBy(() -> SessionUserStatus.valueOfSessionUserStatus(status))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("신청, 승인, 거절만 가능합니다. 현재 상태 : " + status);
  }

  @Test
  @DisplayName("신청, 승인, 거절 상태 확인")
  public void check_신청_승인_거절() {
    assertAll(
            () -> assertThat(SessionUserStatus.valueOfSessionUserStatus("신청")).isEqualTo(SessionUserStatus.REQUEST),
            () -> assertThat(SessionUserStatus.valueOfSessionUserStatus("승인")).isEqualTo(SessionUserStatus.APPROVAL),
            () -> assertThat(SessionUserStatus.valueOfSessionUserStatus("거절")).isEqualTo(SessionUserStatus.REJECTION)
    );

  }
}
