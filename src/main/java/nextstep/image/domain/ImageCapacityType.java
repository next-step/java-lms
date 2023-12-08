package nextstep.image.domain;

import java.util.Arrays;
import nextstep.image.exception.CannotFindImageCapacityTypeException;

public enum ImageCapacityType {

    KB("kb"),
    MB("mb"),
    GB("gb");

    public static final String CANNOT_FIND_IMAGE_CAPACITY_TYPE_EXCEPTION = "이미지 용량 타입을 찾을 수 없습니다.";

    private final String name;

    ImageCapacityType(String name) {
        this.name = name;
    }

    public static ImageCapacityType findByName(String name) {
        return Arrays.stream(values())
                .filter(imageCapacityType -> imageCapacityType.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new CannotFindImageCapacityTypeException(CANNOT_FIND_IMAGE_CAPACITY_TYPE_EXCEPTION));
    }

    public boolean isLessThanMB() {
        return !this.equals(KB);
    }

    public boolean isGreaterThanMB() {
        return this.equals(GB);
    }
}
