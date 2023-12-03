package nextstep.courses.domain;

import java.io.File;

public class SessionImage {
    private long imageSize;
    private String imageType;
    private Integer imageWidth;
    private Integer imageHeight;


    public SessionImage() {
    }

    public SessionImage(File file) {
        validate(file);
        this.imageSize = file.getFreeSpace();
        this.imageType = imageType;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    private void validate(File file) {
        if (file.getTotalSpace() > 1024) {
            throw new IllegalArgumentException();
        }
    }


}
