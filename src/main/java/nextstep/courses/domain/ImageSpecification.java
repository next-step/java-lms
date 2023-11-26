package nextstep.courses.domain;

import nextstep.courses.exception.InvalidImageSpecificationException;

import java.math.BigDecimal;
import java.util.Objects;

public class ImageSpecification {

    private static final BigDecimal DEFAULT_RATIO = BigDecimal.valueOf(1.5);

    private final int width;
    private final int height;

    public ImageSpecification(int width, int height) {
        this.width = width;
        this.height = height;
        validate();
    }

    private void validate() {
        validateSpecification();
        validateSpecificationRatio();
    }

    private void validateSpecification() {
        if (this.width < 300 || this.height < 200) {
            throw new InvalidImageSpecificationException("이미지 규격 조건을 만족하지 않습니다.");
        }
    }

    private void validateSpecificationRatio() {
        if (ratio().compareTo(DEFAULT_RATIO) != 0) {
            throw new InvalidImageSpecificationException("이미지 비율 조건을 만족하지 않습니다.");
        }
    }

    private BigDecimal ratio() {
        return BigDecimal.valueOf(this.width)
            .divide(BigDecimal.valueOf(this.height));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageSpecification that = (ImageSpecification) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
