package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SessionRecruitmentStatusTest {

  @ParameterizedTest(name = "모집 상태가 null일 경우 모집중 상태 반환")
  @NullSource
  public void check_null(String status) {
    assertThat(SessionRecruitmentStatus.valueOfSessionRecruitmentStatus(status)).isEqualTo(SessionRecruitmentStatus.RECRUITING);
  }

  @ParameterizedTest(name = "빈 문자열일 경우 IllegalArgumentException throw")
  @EmptySource
  public void check_empty(String status) {
    assertThatThrownBy(() -> SessionRecruitmentStatus.valueOfSessionRecruitmentStatus(status))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("모집중, 비모집중만 가능합니다.");
  }

  @Test
  @DisplayName("모집중, 비모집중 상태 확인")
  public void check_모집중_and_비모집즁() {
    assertAll(
            () -> assertThat(SessionRecruitmentStatus.valueOfSessionRecruitmentStatus("모집중")).isEqualTo(SessionRecruitmentStatus.RECRUITING),
            () -> assertThat(SessionRecruitmentStatus.valueOfSessionRecruitmentStatus("비모집중")).isEqualTo(SessionRecruitmentStatus.NOT_RECRUITING)
    );
  }
}
