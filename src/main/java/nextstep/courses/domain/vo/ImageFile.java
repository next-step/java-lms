package nextstep.courses.domain.vo;

import nextstep.courses.domain.vo.Extension;

import java.util.Arrays;

public class ImageFile {
    private String fileName;
    private Extension extension;

    public ImageFile(String fileName, Extension extension) {
        this.fileName = fileName;
        this.extension = extension;
    }

    public ImageFile(String file) {
        String[] split = splitFileName(file);
        this.fileName = split[0];
        this.extension = Extension.of(split[1]);
    }

    public boolean satisfy(Extension ... extensions) {
        return Arrays.stream(extensions)
                .anyMatch(it -> it == this.extension);
    }

    private String[] splitFileName(String file) {
        String[] split = file.split("\\.");
        if (split.length < 2) {
            throw new IllegalArgumentException(String.format("%s is not file name format", file));
        }
        return split;
    }
}
