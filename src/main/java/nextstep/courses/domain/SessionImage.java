package nextstep.courses.domain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SessionImage {
    public static final int MB = 1024 * 1024;
    public static final List<String> IMG_FILE_TYPE = List.of("gif", "jpg", "jpeg", "png", "svg");
    private long fileSize;
    private String fileType;
    private ImageSize imageSize;


    public SessionImage() {
    }

    public SessionImage(File file) {
        this.fileSize = validateFileSize(file);
        this.fileType = validateFileType(file);
        this.imageSize = validateImageSize(file);
    }

    public SessionImage(long fileSize, String fileType, ImageSize imageSize) {
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.imageSize = imageSize;
    }

    private Long validateFileSize(File file) {
        if (file.getTotalSpace() > 1*MB) {
            throw new IllegalArgumentException();
        }
        return file.getTotalSpace();
    }

    private String validateFileType(File file) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!IMG_FILE_TYPE.contains(fileType)) {
            throw new IllegalArgumentException();
        }
        return fileType;
    }

    private ImageSize validateImageSize(File file) {
        BufferedImage image = null; 
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageSize(image);
    }

}
