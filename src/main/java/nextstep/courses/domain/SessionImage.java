package nextstep.courses.domain;

import java.util.List;

public class SessionImage {
    private final String path;
    private final int width;
    private final int height;
    private final byte[] file;

    protected SessionImage(String path) {
        this(path, 300, 200, new byte[300 * 200]);
    }

    public SessionImage(String path, int width, int height, byte[] file) {
        if (file.length > 1024 * 1024) {
            throw new IllegalArgumentException("File size should not exceed 1mb");
        }

        List<String> extensions = List.of("gif", "jpg", "jpeg", "png", "svg");
        String extension = path.substring(path.lastIndexOf(".") + 1);
        if (!extensions.contains(extension)) {
            throw new IllegalArgumentException("File extension does not match");
        }

        if (width < 300 || height < 200 || width * 2 != height * 3) {
            throw new IllegalArgumentException("Image width and height must be greater than or equal to 300x200 and 3: 2 ratio");
        }

        this.path = path;
        this.width = width;
        this.height = height;
        this.file = file;
    }
}
