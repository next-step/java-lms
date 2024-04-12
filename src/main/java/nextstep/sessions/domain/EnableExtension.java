package nextstep.sessions.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EnableExtension {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG,
    INVALID
    ;

    private static final Map<String, EnableExtension> MAPPER;
    private static final String COMMA = ".";

    static {
        MAPPER = Arrays.stream(values())
                .collect(Collectors.toMap(Enum::name, Function.identity()));
    }

    public static EnableExtension from(String fileName) {
        if (isBlankFileName(fileName)) {
            return INVALID;
        }

        return MAPPER.getOrDefault(extractExtension(fileName), INVALID);
    }

    private static boolean isBlankFileName(String fileName) {
        return fileName == null || fileName.isBlank();
    }

    private static String extractExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(COMMA) + 1)
                .toUpperCase();
    }
}
