package nextstep.courses.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Image {
    private static final Set<String> ALLOWED_FILE_FORMAT = new HashSet<>(List.of("gif", "jpg", "jpeg", "png", "svg"));
    private final File file;
    private String imageUrl;
    private int width;
    private int height;

    public Image(float fileSize, String fileType, String imageUrl, int width, int height) {
        this.file = new File(ALLOWED_FILE_FORMAT, fileSize, fileType);
        this.imageUrl = imageUrl;
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
        if (file.getSize() > 1024) {
            throw new IllegalArgumentException("FileSize Should be under or equal to 1MB");
        }
    }

    private void validateFileType() {
        if (!file.allowedFileType(file.getType())) {
            throw new IllegalArgumentException("Allowed file types are only gif, jpg/jpeg,png, svg");
        }
    }

    private void validateFileRatio() {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("The ratio of width:height must be 3:2");
        }
    }

    public float getSize() {
        return file.getSize();
    }

    public String getType(){
        return file.getType();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



}
