package nextstep.courses.domain;

import java.util.List;

public class SessionImage {
    private static final List<String> ALLOWED_EXTENSIONS = List.of("gif", "jpg", "jpeg", "png", "svg");
    private static final int MAX_FILE_SIZE_BYTES = 1024 * 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double WIDTH_HEIGHT_RATIO = 1.5;
    private final String path;
    private final int width;
    private final int height;
    private final byte[] file;

    protected SessionImage(String path) {
        this(path, 300, 200, new byte[300 * 200]);
    }

    public SessionImage(String path, int width, int height, byte[] file) {
        validateSize(file);
        validateExtension(path);
        validateDimensions(width, height);

        this.path = path;
        this.width = width;
        this.height = height;
        this.file = file;
    }

    private static void validateSize(byte[] file) {
        if (file.length > MAX_FILE_SIZE_BYTES)
            throw new IllegalArgumentException("File size should not exceed 1mb");
    }

    private static void validateExtension(String path) {
        if (!ALLOWED_EXTENSIONS.contains(extractExtension(path)))
            throw new IllegalArgumentException("File extension does not match");
    }

    private static String extractExtension(String path) {
        return path.substring(path.lastIndexOf(".") + 1).toLowerCase();
    }

    private static void validateDimensions(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT || calculateRatio(width, height) != WIDTH_HEIGHT_RATIO)
            throw new IllegalArgumentException("Image width and height must be greater than or equal to 300x200 and 3: 2 ratio");
    }

    private static double calculateRatio(int width, int height) {
        return (double) width / height;
    }

}
