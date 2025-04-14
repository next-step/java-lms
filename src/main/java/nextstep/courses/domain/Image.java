package nextstep.courses.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Image {
    private static final Set<String> ALLOWED_FILE_TYPES = new HashSet<>(List.of("gif", "jpg", "jpeg", "png", "svg"));
    private float fileSize;
    private String fileType;
    private int width;
    private int height;

    public Image(float fileSize, String fileType, int width, int height) {
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.width = width;
        this.height = height;
        validate();
    }

    private void validate() {
        validateFileSize();
        validateFileType();
        validateFileRatio();
    }

    private void validateFileSize() {
        if (fileSize > 1024) {
            throw new IllegalArgumentException("FileSize Should be under or equal to 1MB");
        }
    }

    private void validateFileType() {
        if (!ALLOWED_FILE_TYPES.contains(fileType)) {
            throw new IllegalArgumentException("Allowed file types are only gif, jpg/jpeg,png, svg");
        }
    }

    private void validateFileRatio() {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("The ratio of width:height must be 3:2");
        }
    }


}
