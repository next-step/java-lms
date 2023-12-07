package nextstep.image.domain;

import static nextstep.image.domain.ImageCapacityType.MB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.image.exception.OutOfRangeCapacityException;
import nextstep.image.exception.OutOfRangeCapacityTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageCapacityTest {

    @Test
    @DisplayName("이미지 용량은 1MB 이하여야 한다.")
    void validate_image_capacity() {
        // when
        ImageCapacity result = new ImageCapacity(1, "mb");

        // then
        assertThat(result).isEqualTo(new ImageCapacity(1, MB));
    }

    @Test
    @DisplayName("이미지 용량 타입이 mb보다 큰 타입이면 예외를 던진다.")
    void validate_image_capacity_type() {
        // when // then
        assertThatThrownBy(() -> new ImageCapacity(1, "gb"))
                .isExactlyInstanceOf(OutOfRangeCapacityTypeException.class)
                .hasMessage(ImageCapacity.OUT_OF_RANGE_CAPACITY_TYPE_EXCEPTION);
    }

    @Test
    @DisplayName("이미지 용량이 1MB 보다 크면 예외를 던진다.")
    void validate_image_capacity_range() {
        // when // then
        assertThatThrownBy(() -> new ImageCapacity(2, "mb"))
                .isExactlyInstanceOf(OutOfRangeCapacityException.class)
                .hasMessage(ImageCapacity.OUT_OF_RANGE_CAPACITY_EXCEPTION);
    }
}
