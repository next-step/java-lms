package nextstep.lms.domain;

import java.util.Arrays;

public class Utils {
    public static boolean isNullOrBlank(String... inputs) {
        return Arrays.stream(inputs)
                .anyMatch(input -> input == null || input.isBlank());
    }

    public static void validateTile(String title) {
        if (isNullOrBlank(title)) {
            throw new IllegalArgumentException("제목을 미입력하였습니다.");
        }
    }
}
