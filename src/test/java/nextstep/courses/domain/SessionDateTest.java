package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionDateTest {
    @Test
    void 생성자테스트() {
        Assertions.assertThat(new SessionDate("20230601", "20230630")).isInstanceOf(SessionDate.class);
    }

    @ParameterizedTest(name = "{displayName} [{index}] {arguments}")
    @NullAndEmptySource
    void 시작일자_NullOrEmpty(String startDate) {
        assertThatThrownBy(() -> {
            SessionDate sessionDate = new SessionDate(startDate, "20230531");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("시작일자가 Null 또는 빈값 입니다.");
    }

    @ParameterizedTest(name = "{displayName} [{index}] {arguments}")
    @NullAndEmptySource
    void 종료일자_NullOrEmpty(String endDate) {
        assertThatThrownBy(() -> {
            SessionDate sessionDate = new SessionDate("20230701", endDate);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("종료일자가 Null 또는 빈값 입니다.");
    }

    @ParameterizedTest(name = "{displayName} [{index}] {arguments}")
    @ValueSource(strings={"20231331", "20AA0301", "2021012", "202305281"})
    void 시작일자포맷검증(String startDate) {
        assertThatThrownBy(() -> {
            SessionDate sessionDate = new SessionDate(startDate, "20230731");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("강의 시작일이 유효한 일자가 아닙니다.");
    }

    @ParameterizedTest(name = "{displayName} [{index}] {arguments}")
    @ValueSource(strings={"20231331", "20AA0301", "2021012", "202305281"})
    void 종료일자포맷검증(String endDate) {
        assertThatThrownBy(() -> {
            SessionDate sessionDate = new SessionDate("20230701", endDate);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("강의 종료일이 유효한 일자가 아닙니다.");
    }

    @Test
    void 일자역전() {
        assertThatThrownBy(() -> {
            SessionDate sessionDate = new SessionDate("20230702", "20230701");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("종료일은 시작일보다 과거일 수 없습니다.");
    }

    @Test
    void 시작일자과거() {
        assertThatThrownBy(() -> {
            SessionDate sessionDate = new SessionDate("20220501", "20230731");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("시작일은 현재보다 과거일 수 없습니다.");
    }

    @Test
    void 강의일자종료체크() {
        SessionDate sessionDate = new SessionDate("20230401", "20230431", false);

        Assertions.assertThat(sessionDate.isExpired()).isTrue();
    }
}
