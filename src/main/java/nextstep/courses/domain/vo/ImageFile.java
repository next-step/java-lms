package nextstep.courses.domain.vo;

import nextstep.courses.domain.vo.Extension;

import java.util.Arrays;

public class ImageFile {
    private static final Extension[] ALLOWED_EXTENSION = {Extension.PNG, Extension.GIF, Extension.JPG, Extension.JPEG, Extension.SVG};

    private String fileName;
    private Extension extension;

    public ImageFile(String fileName, Extension extension) {
        if (!satisfy(extension)) {
            throw new IllegalArgumentException("capacity must under 1mb");
        }
        this.fileName = fileName;
        this.extension = extension;
    }

    public ImageFile(String file) {
        String[] split = splitFileName(file);
        new ImageFile(split[0], Extension.of(split[1]));
    }

    private boolean satisfy(Extension extension) {
        return Arrays.stream(ALLOWED_EXTENSION)
                .anyMatch(it -> it == extension);
    }

    private String[] splitFileName(String file) {
        String[] split = file.split("\\.");
        if (split.length < 2) {
            throw new IllegalArgumentException(String.format("%s is not file name format", file));
        }
        return split;
    }
}
