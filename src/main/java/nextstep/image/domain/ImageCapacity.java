package nextstep.image.domain;

import java.util.Objects;
import nextstep.image.exception.OutOfRangeCapacityException;
import nextstep.image.exception.OutOfRangeCapacityTypeException;

public class ImageCapacity {

    public static final String OUT_OF_RANGE_CAPACITY_EXCEPTION = "이미지 용량이 1MB를 넘습니다.";
    public static final String OUT_OF_RANGE_CAPACITY_TYPE_EXCEPTION = "이미지 용량 타입이 MB를 넘습니다.";

    private long value;
    private ImageCapacityType imageCapacityType;

    public ImageCapacity(long value, ImageCapacityType imageCapacityType) {
        validateImageCapacity(value, imageCapacityType);
        this.value = value;
        this.imageCapacityType = imageCapacityType;
    }

    public ImageCapacity(long value, String capacityTypeName) {
        this(value, ImageCapacityType.findByName(capacityTypeName));
    }

    private static void validateImageCapacity(long value, ImageCapacityType imageCapacityType) {
        validateCapacityTypeRange(imageCapacityType);
        validateCapacityRange(value, imageCapacityType);
    }

    private static void validateCapacityTypeRange(ImageCapacityType imageCapacityType) {
        if (imageCapacityType.isGreaterThanMB()) {
            throw new OutOfRangeCapacityTypeException(OUT_OF_RANGE_CAPACITY_TYPE_EXCEPTION);
        }
    }

    private static void validateCapacityRange(long value, ImageCapacityType imageCapacityType) {
        if (outOfRange(value, imageCapacityType)) {
            throw new OutOfRangeCapacityException(OUT_OF_RANGE_CAPACITY_EXCEPTION);
        }
    }

    private static boolean outOfRange(long value, ImageCapacityType imageCapacityType) {
        return imageCapacityType.isLessThanMB() && value > 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImageCapacity that = (ImageCapacity) o;
        return value == that.value && imageCapacityType == that.imageCapacityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, imageCapacityType);
    }
}
