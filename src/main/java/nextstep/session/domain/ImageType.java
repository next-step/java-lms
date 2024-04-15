package nextstep.session.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ImageType {
    gif, jpg, jpeg, png, svg;

    public static boolean validateType(String input) {
        return Arrays.stream(ImageType.values())
            .anyMatch(imageType -> imageType.isSame(input.toLowerCase()));
    }

    public static String nameConcat(String delimiter) {
        return Arrays.stream(ImageType.values()).map(Enum::name).collect(
            Collectors.joining(delimiter));
    }

    public boolean isSame(String type) {
        return this.name() == type;
    }
}
