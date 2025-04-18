package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MaxAttendeeCountTest {

    @Test
    @DisplayName("같은 값의 MaxAttendeeCount 객체를 비교하면 equals는 true를 반환한다.")
    void equals_shouldReturnTrue_whenObjectsHaveSameMaxAttendeeCount() {
        MaxAttendeeCount count1 = new MaxAttendeeCount(10);
        MaxAttendeeCount count2 = new MaxAttendeeCount(10);

        Assertions.assertThat(count1).isEqualTo(count2);
    }

    @Test
    @DisplayName("다른 값의 MaxAttendeeCount 객체를 비교하면 equals는 false를 반환한다.")
    void equals_shouldReturnFalse_whenObjectsHaveDifferentMaxAttendeeCount() {
        MaxAttendeeCount count1 = new MaxAttendeeCount(10);
        MaxAttendeeCount count2 = new MaxAttendeeCount(20);

        Assertions.assertThat(count1).isNotEqualTo(count2);
    }

    @Test
    @DisplayName("MaxAttendeeCount 객체를 null과 비교하면 equals는 false를 반환한다.")
    void equals_shouldReturnFalse_whenComparingWithNull() {
        MaxAttendeeCount count = new MaxAttendeeCount(10);

        Assertions.assertThat(count).isNotEqualTo(null);
    }

    @Test
    @DisplayName("MaxAttendeeCount 객체를 다른 클래스 객체와 비교하면 equals는 false를 반환한다.")
    void equals_shouldReturnFalse_whenComparingWithDifferentClassObject() {
        MaxAttendeeCount count = new MaxAttendeeCount(10);

        Assertions.assertThat(count).isNotEqualTo("DifferentClassObject");
    }

    @Test
    @DisplayName("maxAttendeeCount가 0 이하일 때 생성자는 IllegalArgumentException을 던진다.")
    void constructor_shouldThrowException_whenMaxAttendeeCountIsZeroOrNegative() {
        Assertions.assertThatThrownBy(() -> new MaxAttendeeCount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강생 수는 0보다 커야 합니다.");

        Assertions.assertThatThrownBy(() -> new MaxAttendeeCount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강생 수는 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("maxAttendeeCount가 양수일 때 생성자는 성공적으로 객체를 생성한다.")
    void constructor_shouldCreateObject_whenMaxAttendeeCountIsPositive() {
        MaxAttendeeCount count = new MaxAttendeeCount(10);

        Assertions.assertThat(count).isNotNull();
    }
}