package nextstep.qna.domain;

import nextstep.courses.InvalidImageSpecificationException;
import nextstep.courses.domain.ImageSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImageSpecificationTest {

    @Test
    @DisplayName("이미지 규격이 조건에 맞지 않을 경우 에러 발생한다")
    public void image_specification_condition() {
        assertThatExceptionOfType(InvalidImageSpecificationException.class)
            .isThrownBy(() -> new ImageSpecification(150, 100))
            .withMessageMatching("이미지 규격 조건을 만족하지 않습니다.");
    }

    @Test
    @DisplayName("이미지 규격의 비율이 맞지 않을 경우 에러 발생한다")
    public void image_specification_ratio_condition() {
        assertThatExceptionOfType(InvalidImageSpecificationException.class)
            .isThrownBy(() -> new ImageSpecification(400, 800))
            .withMessageMatching("이미지 비율 조건을 만족하지 않습니다.");
    }

}
